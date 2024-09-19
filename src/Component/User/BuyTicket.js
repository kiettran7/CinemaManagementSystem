import React, { useEffect } from "react";
import './Style.css';
import { Box, Grid, Typography, Stepper, StepLabel, Step, Container } from "@mui/material";
import BuyTicketStep1Page from "./BuyTicketStep1";
import BuyTicketStep2Page from "./BuyTicketStep2";
import BuyTicketStep3Page from "./BuyTicketStep3";
import BuyTicketStep4Page from "./BuyTicketStep4";
import { useBuyTicketContext } from "../../Context/BuyTicketContext";
import { useMovieContext } from "../../Context/MovieContext";


function BuyTicket() {
    const { steps, activeStep } = useBuyTicketContext();
    const { selectedMovie, setSelectedMovie } = useMovieContext();

    const getStepContent = (step) => {
        switch(step) {
            case 0:
                return <BuyTicketStep1Page />
            case 1:
                return <BuyTicketStep2Page />
            case 2:
                return <BuyTicketStep3Page />
            case 3:
                return <BuyTicketStep4Page />
            default:
                return <BuyTicketStep3Page />
        }
    }

    useEffect(() => {
        // Kiểm tra localStorage để khôi phục selectedMovie
        const movieFromLocalStorage = localStorage.getItem('selectedMovie');
        if (movieFromLocalStorage) {
            setSelectedMovie(JSON.parse(movieFromLocalStorage));
        }
    }, []);

    return (
        <>
        <Container maxWidth="lg" className="mt-30 mb-60">
            <Box component="stepper">
                <Stepper activeStep={activeStep} alternativeLabel>
                    {steps.map((label) => (
                    <Step key={label}>
                        <StepLabel>{label}</StepLabel>
                    </Step>
                    ))}
                </Stepper>
            </Box>

            <Box component="card">
                <Box sx={{ mt: 5 }} className="radius ticket-info-p ticket-border-box">
                    <Grid container>
                        <Grid item xs={3} md={3}>
                            <img src={selectedMovie ? selectedMovie.img : ''}
                                alt="movie" className="ticket-img">
                            </img>
                        </Grid>

                        <Grid item xs={6} md={9}>
                            <Typography className="text-title text-main" variant="h4">
                                {selectedMovie ? selectedMovie.title : ''}
                            </Typography>

                            <Typography variant="body1" className="text-normal"> 
                                {selectedMovie ? selectedMovie.category : ''}
                            </Typography>

                            <Typography variant="body1" className="text-normal">
                                Các thông tin khác.
                            </Typography>
                        </Grid>
                    </Grid>
                </Box>
            </Box>

            {getStepContent(activeStep)}

        </Container>
        </>
    );
};

export default BuyTicket;