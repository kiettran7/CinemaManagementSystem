import './App.css';
import React, { useState } from 'react';
import { AppBar, Button, Container, Toolbar, useScrollTrigger, CssBaseline, styled, alpha, InputBase, Box, Link, Typography, IconButton, Table, TableContainer, Paper, TableHead, TableRow, TableCell, TableBody, Stack } from "@mui/material";
import { Route, Routes } from "react-router-dom";
import PropTypes from 'prop-types';
import HomePage from './Component/User/Home';
import SearchIcon from '@mui/icons-material/Search';
import { Dropdown, DropdownButton } from 'react-bootstrap';
import BuyTicketPage from './Component/User/BuyTicket';

function ElevationScroll(props) {
    const { children, window } = props;

    const trigger = useScrollTrigger({
        disableHysteresis: true,
        threshold: 0,
        target: window ? window() : undefined,
    });
  
    return React.cloneElement(children, {
        elevation: trigger ? 4 : 0,
        style: {
            backgroundColor: trigger ? '#191b21' : 'rgba(0, 0, 0, 0.1)',
            transition: 'background-color 0.3s',
        },
    });
}
  
ElevationScroll.propTypes = {
    children: PropTypes.element.isRequired,
    window: PropTypes.func,
};



function App(props) {
    const Search = styled('div')(({ theme }) => ({
        position: 'relative',
        borderRadius: theme.shape.borderRadius,
        backgroundColor: alpha(theme.palette.common.white, 0.15),
        '&:hover': {
            backgroundColor: alpha(theme.palette.common.white, 0.25),
        },
        marginLeft: 0,
        width: '100%',
        [theme.breakpoints.up('sm')]: {
            marginLeft: theme.spacing(1),
            width: 'auto',
        },
    }));
        
    const SearchIconWrapper = styled('div')(({ theme }) => ({
        padding: theme.spacing(0, 2),
        height: '100%',
        position: 'absolute',
        pointerEvents: 'none',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    }));
        
    const StyledInputBase = styled(InputBase)(({ theme }) => ({
        color: 'inherit',
        width: '100%',
        '& .MuiInputBase-input': {
        padding: theme.spacing(1, 1, 1, 0),
        // vertical padding + font size from searchIcon
        paddingLeft: `calc(1em + ${theme.spacing(4)})`,
        transition: theme.transitions.create('width'),
        [theme.breakpoints.up('sm')]: {
            width: '12ch',
            '&:focus': {
            width: '20ch',
            },
        },
        },
    }));

    const [ open, setOpen ] = useState(false);

    const openChat = () => {
        setOpen(true);
    }

    const closeChat = () => {
        setOpen(false);
    }
   
    return (
        <>
        <CssBaseline />
        <ElevationScroll {...props}> 
            <AppBar position='fixed'>
                <Container maxWidth="lg">
                    <Toolbar style={{ padding: 0 }} className='text-center'>
                        <Box component="logo" sx={{ p: 1 , width: '150px' }}>
                            <Link href="/" underline="none">
                                <img className='header-height' alt="Logo"
                                    src="https://i.pinimg.com/originals/f1/30/f9/f130f9773716f8c688005c24dd41404d.png" />
                            </Link>
                        </Box>

                        <Box component="navbar" sx={{ p: 1, flexGrow: 1 }}>
                            <Button color="inherit" className='header-height btn-header-width'>
                                LỊCH CHIẾU
                            </Button>

                            <Button color="inherit" className='header-height btn-header-width'>
                                ƯU ĐÃI
                            </Button>

                            <Button color="inherit" className='header-height btn-header-width'>
                                <DropdownButton id="dropdown-button" title="THỂ LOẠI">
                                <Dropdown.Item href="/buy-ticket/step1" className='dropdown-item'>Action</Dropdown.Item>
                                <Dropdown.Item href="#/action-2" className='dropdown-item'>Another action</Dropdown.Item>
                                <Dropdown.Item href="#/action-3" className='dropdown-item'>Something else</Dropdown.Item>
                                </DropdownButton>
                            </Button>
                        </Box>

                        <Box component="search" sx={{ mr: 3 }}>
                            <Search>
                                <SearchIconWrapper>
                                    <SearchIcon />
                                </SearchIconWrapper>
                                <StyledInputBase placeholder="Search…" inputProps={{ 'aria-label': 'search' }} />
                            </Search>
                        </Box>

                        <Button color="inherit" variant="outlined" className='btn-member header-height'>THÀNH VIÊN</Button>
                    </Toolbar>
                </Container>

                <Box sx={{ position: 'fixed', bottom: 0, right: 30, bottom: 30 }}>
                    <IconButton aria-label="message" className="btn-message" size='large' onClick={openChat}>
                        <img width="32" height="32" src="https://img.icons8.com/color/48/filled-chat.png" alt="filled-chat"/>
                    </IconButton>
                </Box>
            </AppBar>
        </ElevationScroll>

        <Toolbar />

        <Routes>
            <Route exact path="/" Component={HomePage} />
            <Route path="/buy-ticket/*" Component={BuyTicketPage} />
        </Routes>
        
        <footer className="footer">
            <Container maxWidth="lg">
                <Box component="copyright">
                    <Typography variant="body1" color="textSecondary" align="center" className="text-white mb-20">
                        Copyright ©2024, KLH Cinema
                    </Typography>
                </Box>

                <Box component="social">
                    <Stack direction="row" spacing={2} justifyContent="center">
                        <img width="48" height="48" src="https://img.icons8.com/color/48/facebook-new.png" alt="facebook-new"/>
                        <img width="48" height="48" src="https://img.icons8.com/color/48/instagram-new--v1.png" alt="instagram-new--v1"/>
                        <img width="48" height="48" src="https://img.icons8.com/color/48/tiktok--v1.png" alt="tiktok--v1"/>
                        <img width="48" height="48" src="https://img.icons8.com/color/48/youtube-play.png" alt="youtube-play"/>
                    </Stack>
                </Box>
            </Container>
        </footer>
        </>
    );
};

export default App;