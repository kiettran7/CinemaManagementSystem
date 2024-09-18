import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Style.css';
import LocalMoviesIcon from '@mui/icons-material/LocalMovies';
import { Avatar, Box, Container, IconButton, InputAdornment, TextField, Typography, Button, Grid } from '@mui/material';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import PersonIcon from '@mui/icons-material/Person';
import LockIcon from '@mui/icons-material/Lock';


function Signup() {
    const [showPassword, setShowPassword] = useState({});
    const navigate = useNavigate();

    const handleClickShowPassword = (field) => {
        setShowPassword((prev) => ({
            ...prev,
            [field]: !prev[field],
        }));
    };

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    return (
        <>
        <Box className="l-wrapper l-height">
            <Typography variant="h2" component="div" className="text-center" sx={{ pt: 5, pb: 5 }}>
                    ĐẶT VÉ NGAY HÔM NAY
            </Typography>

            <Container maxWidth="md" className="l-container l-center">
                <Box className="l-box">
                    <Avatar className="l-icon" sx={{ width: 50, height: 50 }}>
                        <LocalMoviesIcon />
                    </Avatar>

                    <Typography variant="h6" component="div" gutterBottom color="black">
                        ĐĂNG KÝ THÀNH VIÊN
                    </Typography>

                    <Box component="form" className="l-form" noValidate sx={{ '& .MuiTextField-root': { mt:4, width: '100%' } }}>
                        <Grid container spacing={10}>
                            <Grid item xs={6}>
                                <div>
                                    <TextField required label="Họ" id="first-name" name="first-name" placeholder="Họ" autoFocus />
                            
                                    <TextField required label="Tên đăng nhập" id="username" name="username" placeholder="Tên đăng nhập" />

                                    <TextField required label="Mật khẩu" id="password" name="password"  placeholder="Mật khẩu"
                                        type={showPassword['password'] ? 'text' : 'password'} InputProps={{   
                                            endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton edge="end" onClick={() => handleClickShowPassword('password')} onMouseDown={handleMouseDownPassword} className="btn-scale" >
                                                    {showPassword['password'] ? <VisibilityOff /> : <Visibility />}
                                                </IconButton>
                                            </InputAdornment>
                                            ),
                                        }} />
                                </div>
                            </Grid>

                            <Grid item xs={6}>
                                <div>
                                    <TextField required label="Tên đệm và tên" id="last-name" name="last-name" placeholder="Tên đệm và tên" />
                                    
                                    <TextField required label="Email" id="email" name="email" placeholder="Email" />
                                    
                                    <TextField required label="Xác nhận lại mật khẩu" id="re-password" name="re-password"
                                        placeholder="Xác nhận lại mật khẩu" type={showPassword['re-password'] ? 'text' : 'password'} InputProps={{   
                                            endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton edge="end" onClick={() => handleClickShowPassword('re-password')} onMouseDown={handleMouseDownPassword} className="btn-scale" >
                                                    {showPassword['password'] ? <VisibilityOff /> : <Visibility />}
                                                </IconButton>
                                            </InputAdornment>
                                            ),
                                        }} />
                                </div>
                            </Grid>
                        </Grid>

                        <Button type="submit" fullWidth variant="contained" className="l-btn btn-sign-up">
                            Đăng ký
                        </Button>

                        <Link to="login">
                            <Button fullWidth variant="contained" className="l-btn btn-mt">
                                Đăng nhập
                            </Button>
                        </Link>
                    </Box>
                </Box>
            </Container>
        </Box>
        </>
    );
};

export default Signup;