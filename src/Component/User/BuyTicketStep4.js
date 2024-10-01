import React, { useEffect, useState } from "react";
import './Style.css';
import { Box, Grid, Typography, Stack, Button, FormControl, FormLabel, RadioGroup, FormControlLabel, Radio, Autocomplete, TextField } from "@mui/material";
import { useMovieContext } from "../../Context/MovieContext";
import { useBuyTicketContext } from "../../Context/BuyTicketContext";
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';
import { useLayoutContext } from '../../Context/LayoutContext';
import Api, { endpoints } from "../../Api";

function BuyTicketStep4() {
    const { steps, activeStep, nextStep, backStep, minutes, seconds, formatTime, selectedSeats, totalPriceSeats,
        selectedFoods, quantityFoods, totalPriceFoods, totalPrice, ticketInfo, setTicketInfo } = useBuyTicketContext();
    const { selectedMovie } = useMovieContext();
    const { setIsLoading } = useLayoutContext();

    const [promotions, setPromotions] = useState([]);

    const sortSeats = (a, b) => {
        const numA = parseInt(a.seatName.replace(/\D/g, ''));
        const numB = parseInt(b.seatName.replace(/\D/g, ''));
        return numA - numB;
    };

    const handlePayMethod = (e) => {
        const selectedMethod = e.target.value;
        setTicketInfo((prevInfo) => ({
            ...prevInfo,
            method: selectedMethod,
        }));
    };

    const fetchPromotions = async () => {
        setIsLoading(true);

        try {
            const res = await Api.get(endpoints.promotions);
            const data = res.data.result;

            if (Array.isArray(data)) {
                setPromotions(data);
                localStorage("promotions", JSON.stringify(data));
            } else {
                console.log("Không có danh sách");
            }
        } catch (error) {
            console.log("Lỗi khi lấy danh sách mã giảm giá");
        } finally {
            setIsLoading(false);
        };
    };

    useEffect(() => {
        fetchPromotions();
    }, [setIsLoading]);

    return (
        <>
        <Box component="main">
            <Grid container sx={{ mt: 3 }} spacing={5}>
                <Grid item xs={8} component="payment">
                    <Box className="radius ticket-border-box">
                        <Box component="methods">
                            <FormControl sx={{ width: '100%' }}>
                                <FormLabel id="radio-payment-methods" className="text-white title-box flex-center">
                                    <Typography variant="h6" className="title-m">
                                        Phương thức thanh toán
                                    </Typography>
                                </FormLabel>

                                <RadioGroup aria-labelledby="radio-payment-methods" name="payment-methods" className="ml-30 mt-10 mb-3" onChange={handlePayMethod}>
                                    <FormControlLabel value="vnpay" control={<Radio className="text-des" />}
                                        label="Thanh toán qua VNPAY (Visa, Master, Amex, JCB,...)" />
                                    <FormControlLabel value="momo" control={<Radio className="text-des" />}
                                        label="Thanh toán bằng Ví điện tử MoMo" />
                                </RadioGroup>
                            </FormControl>
                        </Box>
                    </Box>
                </Grid>

                <Grid item xs={4} component="info">
                <Box className="radius ticket-border-box">
                        <Box className="step2-box-p bb">
                            <Typography gutterBottom variant="h6" component="div">
                                KLH Cinema Nhà Bè
                            </Typography>

                            <Typography variant="body2" component="div" className="text-des text-normal">
                                Khu dân cư Nhơn Đức, Nhà Bè, Thành phố Hồ Chí Minh
                            </Typography>
                        </Box>

                        <Box className="step2-box-p bb">
                            <Typography gutterBottom variant="h6" component="div" className="text-uppercase text-main">
                                {selectedMovie ? selectedMovie.movieName : ''}
                            </Typography>

                            <Typography variant="body2" component="div" className="text-des text-normal">
                                Thời lượng: {selectedMovie ? selectedMovie.duration + " phút" : ''}
                            </Typography>

                            {selectedSeats.length > 0 &&
                                <Box className="mt-15">
                                    <Grid container spacing={1} className="item-detail title-box align-center">
                                        <Grid item xs={7}>
                                            <Typography variant="body2" component="div" className="text-normal">
                                                {selectedSeats.length} x
                                            </Typography>

                                            <Typography variant="body2" component="div" className="text-normal mt-2">
                                                Ghế {selectedSeats.sort(sortSeats).map((seat) => seat.seatName.replace('Phòng ', '')).join(', ')}
                                            </Typography>
                                        </Grid>

                                        <Grid item xs={5} className="text-end">
                                            <Typography variant="body2" component="div" className="text-normal start-end">
                                                {totalPriceSeats.toLocaleString()} VND
                                            </Typography>
                                        </Grid>
                                    </Grid>
                                </Box>
                            }

                            {selectedFoods.map((item) => {
                                const quantity = quantityFoods[item.itemName] || 0;
                                if (quantity > 0) {
                                    const totalPriceFoodName = totalPriceFoods[item.itemName] || 0;
                                    
                                    return (
                                        <Grid container spacing={1} key={item.id} className="item-detail title-box" alignItems="center">
                                            <Grid item xs={7}>
                                                <Typography variant="body2" component="div" className="text-normal">
                                                    {quantity} x
                                                </Typography>

                                                <Typography variant="body2" component="div" className="text-normal mt-2">
                                                    {item.itemName}
                                                </Typography>
                                            </Grid>

                                            <Grid item xs={5} className="text-end">
                                                <Typography variant="body2" component="div" className="text-normal">
                                                    {totalPriceFoodName.toLocaleString()} VND
                                                </Typography>
                                            </Grid>
                                        </Grid>
                                    );
                                } else {
                                    return null;
                                }
                            })}
                        </Box>

                        <Box className="step2-box-p">
                            <Box className="promotion-box mb-20">
                                <Autocomplete multiple id="promotion-tags" options={promotions} getOptionLabel={(option) => option.promotionName}
                                    filterSelectedOptions renderInput={(params) => (
                                        <TextField {...params} label="Chọn mã giảm giá" placeholder="Chọn mã giảm giá" />
                                    )} />
                            </Box>

                            <Stack direction="row" alignItems="center">
                                <Typography variant="h6" component="div" className="text-normal">
                                    Tổng tiền:
                                </Typography>

                                <Typography variant="h5" component="div" className="text-normal start-end">
                                    {totalPrice.toLocaleString()} VND
                                </Typography>
                            </Stack>

                            <Button variant="contained" size="small" className="p-10 btn-bg btn-transition btn-total-money text-title" onClick={() => nextStep(selectedMovie)}>
                                THANH TOÁN ({activeStep + 1}/{steps.length})
                            </Button>

                            <Box component="return" className="text-white flex-center mt-15 return pointer" onClick={backStep}>
                                <KeyboardBackspaceIcon className="mr-5" />
                                <Typography variant="body2" component="div" className="text-normal">
                                    Trở lại
                                </Typography>
                            </Box>

                            <Box component="countdown" className="flex-center mt-15">
                                <Typography variant="body2" component="div" className="text-normal mr-5">
                                    Còn lại
                                </Typography>

                                <Typography variant="body2" component="div" className="text-danger">
                                    {formatTime({ minutes, seconds })}
                                </Typography>
                            </Box>
                        </Box>
                    </Box>
                </Grid>
            </Grid>
        </Box>
        </>
    )
};

export default BuyTicketStep4;