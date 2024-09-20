import React, { useState } from "react";
import './Style.css';
import { Box, Grid, Typography, Stack, Button, FormControl, FormLabel, RadioGroup, FormControlLabel, Radio } from "@mui/material";
import { useMovieContext } from "../../Context/MovieContext";
import { useBuyTicketContext } from "../../Context/BuyTicketContext";
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';

function BuyTicketStep4() {
    const { steps, activeStep, nextStep, backStep, minutes, seconds, formatTime, selectedSeats, getSeatType, totalPriceSeats,
        selectedFoods, quantityFoods, totalPriceFoods, totalPrice } = useBuyTicketContext();
    const { selectedMovie } = useMovieContext();

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

                                <RadioGroup aria-labelledby="radio-payment-methods" name="payment-methods" className="ml-30 mt-10 mb-3">
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
                                Tên rạp
                            </Typography>

                            <Typography variant="body2" component="div" className="text-des text-normal">
                                Địa chỉ
                            </Typography>
                        </Box>

                        <Box className="step2-box-p bb">
                            <Typography gutterBottom variant="h6" component="div" className="text-uppercase text-main">
                                {selectedMovie ? selectedMovie.title : ''}
                            </Typography>

                            <Typography variant="body2" component="div" className="text-des text-normal">
                                {selectedMovie ? selectedMovie.category : ''}
                            </Typography>

                            <Box className="mt-15">
                                {['normal', 'couple', 'vip'].map((seatType) => {
                                    const seatsOfType = selectedSeats.filter((seat) => seat.seatType === seatType);
                                    const price = totalPriceSeats[seatType] || 0;

                                    if (price > 0) {
                                        return (
                                            <Stack key={seatType} direction="row" className="item-detail title-box" alignItems="center">
                                                <Box>
                                                    <Typography variant="body2" component="div" className="text-normal text-uppercase">
                                                        {seatsOfType.length} x Adult - {seatType} - 2D
                                                    </Typography>

                                                    <Typography variant="body2" component="div" className="text-normal mt-2">
                                                        {getSeatType(seatType)}
                                                    </Typography>
                                                </Box>

                                                <Typography variant="body2" component="div" className="text-normal start-end">
                                                    {price.toLocaleString()} VND
                                                </Typography>
                                            </Stack>
                                        );
                                    } else {
                                        return null;
                                    }
                                })}
                            </Box>

                            {selectedFoods.map((item) => {
                                if (quantityFoods[item.name] > 0) {
                                    const totalPriceFoodName = totalPriceFoods[item.name] || 0;
                                    return (
                                        <Stack key={item.id} direction="row" className="item-detail title-box" alignItems="center">
                                            <Box width={200}>
                                                <Typography variant="body2" component="div" className="text-normal">
                                                        {quantityFoods[item.name]} x
                                                </Typography>

                                                <Typography variant="body2" component="div" className="text-normal mt-2">
                                                        {item.name}
                                                </Typography>
                                            </Box>

                                            <Typography variant="body2" component="div" className="text-normal start-end">
                                                    {totalPriceFoodName.toLocaleString()} VND
                                            </Typography>
                                        </Stack>
                                    );
                                } else {
                                    return null;
                                }
                            })}
                        </Box>

                        <Box className="step2-box-p">
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