import React from "react";
import './Style.css';
import { Box, Grid, Typography, Button, Stack } from "@mui/material";
import dayjs from 'dayjs';
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';
import { useBuyTicketContext } from "../../Context/BuyTicketContext";
import { useMovieContext } from "../../Context/MovieContext";

function BuyTicketStep1() {
    const { nextStep, setIsActiveTimer } = useBuyTicketContext();
    const { selectedMovie } = useMovieContext();

    const goStep2 = () => {
        nextStep(selectedMovie);
        setIsActiveTimer(true);
    }

    return (
        <>
        <Box component="booking">
            <Grid container sx={{ mt: 3 }} spacing={5}>
                <Grid item xs={4.5} component="calendar">
                    <Box className="radius flex-center ticket-border-box">
                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                            <DemoContainer components={['DateCalendar']}>
                                <DateCalendar className="ticket-border-box ticket-m" defaultValue={dayjs(Date())} disablePast views={['year', 'month', 'day']} />
                            </DemoContainer>
                        </LocalizationProvider>
                    </Box>
                </Grid>

                <Grid item md={7.5} component="info">
                    <Box className="radius ticket-p ticket-border-box">
                        <Box sx={{ mb: 3 }} className="radius ticket-info-p ticket-border-box">
                            <Typography gutterBottom variant="h6" component="div">
                                    Tên rạp
                            </Typography>

                            <Typography variant="body2" component="div" className="text-normal text-des">
                                    Địa chỉ
                            </Typography>

                            <Stack direction="row" spacing={4} component="showing">
                                <Box component="time">
                                    <Button className="text-center show-time text-white" onClick={goStep2}>
                                        Giờ chiếu
                                    </Button>

                                    <Stack direction="row" spacing={1} className="text-center flex-center mt-10" component="des">
                                        <Typography variant="body1" fontSize={12} className="text-normal border-2 des-box">
                                            Mô tả 1
                                        </Typography>

                                        <Typography variant="body1" fontSize={12} className="text-normal border-2 des-box">
                                            Mô tả 2
                                        </Typography>
                                    </Stack>
                                </Box>

                                <Box component="time">
                                    <Button className="text-center show-time text-white" onClick={goStep2}>
                                        Giờ chiếu
                                    </Button>

                                    <Stack direction="row" spacing={1} className="text-center flex-center mt-10" component="des">
                                        <Typography variant="body1" fontSize={12} className="text-normal border-2 des-box">
                                            Mô tả 1
                                        </Typography>

                                        <Typography variant="body1" fontSize={12} className="text-normal border-2 des-box">
                                            Mô tả 2
                                        </Typography>
                                    </Stack>
                                </Box>

                                <Box component="time">
                                    <Button className="text-center show-time text-white" onClick={goStep2}>
                                        Giờ chiếu
                                    </Button>

                                    <Stack direction="row" spacing={1} className="text-center flex-center mt-10" component="des">
                                        <Typography variant="body1" fontSize={12} className="text-normal border-2 des-box">
                                            Mô tả 1
                                        </Typography>

                                        <Typography variant="body1" fontSize={12} className="text-normal border-2 des-box">
                                            Mô tả 2
                                        </Typography>
                                    </Stack>
                                </Box>
                            </Stack>
                        </Box>
                    </Box>
                </Grid>
            </Grid>
        </Box>
        </>
    );
};

export default BuyTicketStep1;