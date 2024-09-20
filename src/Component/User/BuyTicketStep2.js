import React from "react";
import './Style.css';
import { Box, Typography, Grid, Button, Stack } from "@mui/material";
import Grid2 from "@mui/material/Unstable_Grid2/Grid2";
import WeekendIcon from '@mui/icons-material/Weekend';
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';
import { useBuyTicketContext } from "../../Context/BuyTicketContext";
import { useMovieContext } from "../../Context/MovieContext";


function BuyTicketStep2() {
    const { steps, activeStep, nextStep, backStep1, rowsOfSeat, minutes, seconds, formatTime, handleSelectSeat, selectedSeats, getSeatType,
        totalPriceSeats, selectedFoods, quantityFoods, totalPriceFoods, totalPrice } = useBuyTicketContext();
    const { selectedMovie } = useMovieContext();

    return (    
        <>
        <Box component="main">
            <Grid container sx={{ mt: 3 }} spacing={5}>
                <Grid item xs={8} component="seat">
                    <Box component="line">
                        <Box className="line-first">
                            <Box className="line-second">
                                <Typography variant="h4" mt={2}>Màn hình</Typography>
                            </Box>
                        </Box>
                    </Box>

                    <Grid2 container spacing={3} sx={{ flexGrow: 1, mt: 1 }} component="description">
                        <Grid2 xs={6} xsOffset={3} md={3} mdOffset={0} className="seat-normal align-center">
                            <WeekendIcon className="mr-10 seat" />
                            <Typography variant="body1" className="text-normal">Ghế thường</Typography>
                        </Grid2>

                        <Grid2 xs={4} md={4} mdOffset="auto" className="seat-vip align-center">
                            <WeekendIcon className="mr-10 seat" />
                            <Typography variant="body1" className="text-normal">Ghế VIP</Typography>
                        </Grid2>

                        <Grid2 xs={4} xsOffset={4} md={3} mdOffset={0} className="seat-couple align-center">
                            <WeekendIcon className="mr-10 seat" />
                            <Typography variant="body1" className="text-normal">Ghế đôi</Typography>
                        </Grid2>

                        <Grid2 xs md={3} mdOffset={2} className="seat-select align-center">
                            <WeekendIcon className="mr-10 seat" />
                            <Typography variant="body1" className="text-normal">Ghế đã chọn</Typography>
                        </Grid2>

                        <Grid2 xs md={4} mdOffset={2} className="seat-sold align-center">
                            <WeekendIcon className="mr-10 seat" />
                            <Typography variant="body1" className="text-normal">Ghế đã bán</Typography>
                        </Grid2>
                    </Grid2>

                    <Box component="list">
                        <Box sx={{ mt: 5 }}>
                            {rowsOfSeat.map((row, index) => (
                                <Grid container spacing={0.8} key={index} className="align-center mt-0" component="row">
                                    <Grid item xs={0.5} component="name">
                                        <Typography variant="body1" className="text-normal text-row-seat">{row.row}</Typography>
                                    </Grid>

                                    {Array(row.seats).fill().map((_, seatIndex) => {
                                        const seatSelected = selectedSeats.find((seat) => seat.row === row.row
                                            && seat.seatIndex === seatIndex);
                                        let seatClass = 'seat-normal';

                                        if (row.row === 'K') {
                                            if (seatIndex % 2 !== 0) {
                                                return (
                                                    <Grid item key={seatIndex} className="pointer" onClick={() => handleSelectSeat(row.row, seatIndex, 'couple')}>
                                                        <WeekendIcon className={`seat-couple seat-c ${seatSelected  ? 'seat-select': ''}`} />
                                                        <WeekendIcon className={`seat-couple seat-c ml-3 ${seatSelected  ? 'seat-select': ''}`} />
                                                    </Grid>
                                                );
                                            } else {
                                                return (
                                                    <Grid item key={seatIndex}>
                                                        <WeekendIcon className="seat-normal seat invisible" />
                                                    </Grid>
                                                );
                                            }
                                        } else {
                                            if (['D', 'E', 'F', 'G', 'H', 'I', 'J'].includes(row.row) && seatIndex >= 2 && seatIndex <= 15) {
                                                seatClass = 'seat-vip';
                                            }

                                            return (
                                                <Grid item key={seatIndex} className="pointer" onClick={() => handleSelectSeat(row.row, seatIndex, seatClass === 'seat-vip' ? 'vip' : 'normal')}>
                                                    <WeekendIcon className={`${seatClass} seat ${seatSelected  ? 'seat-select': ''}`} />
                                                </Grid>
                                            );
                                        }
                                    })}

                                    <Grid item xs={0.5} component="name">
                                        <Typography variant="body1" className="text-normal ml-15">{row.row}</Typography>
                                    </Grid>
                                </Grid>
                            ))}
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
                                    const seatPosition = seatsOfType.map((seat) => `${seat.row}${seat.seatIndex + 1}`).join(', ');

                                    if (price > 0) {
                                        return (
                                            <Stack key={seatType} direction="row" className="item-detail title-box" alignItems="center">
                                                <Box>
                                                    <Typography variant="body2" component="div" className="text-normal text-uppercase">
                                                        {seatsOfType.length} x Adult - {seatType} - 2D
                                                    </Typography>

                                                    <Typography variant="body2" component="div" className="text-normal mt-2">
                                                        {seatPosition}
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
                            {selectedSeats.length > 0 ? (
                                <>
                                    <Stack direction="row" alignItems="center">
                                        <Typography variant="h6" component="div" className="text-normal">
                                            Tổng tiền:
                                        </Typography>

                                        {selectedSeats.length > 0 && (
                                            <Typography variant="h5" component="div" className="text-normal start-end">
                                                {totalPrice.toLocaleString()} VND
                                            </Typography>
                                        )}
                                    </Stack>

                                    <Button variant="contained" size="small" className="p-10 btn-bg btn-transition btn-total-money text-title" onClick={() => nextStep(selectedMovie)}>    
                                        CHỌN ĐỒ ĂN ({activeStep + 1}/{steps.length})
                                    </Button>
                                </>
                            ) : (
                                <Typography variant="body2" component="div" className="text-normal none-seat p-1 text-center mt-2">
                                    Bạn chưa chọn ghế. Vui lòng chọn ghế!
                                </Typography>
                            )}

                            <Box component="return" className="text-white flex-center mt-15 return pointer" onClick={backStep1}>
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
    );
};

export default BuyTicketStep2;