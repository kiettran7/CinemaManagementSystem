import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Style.css';
import LocalMoviesIcon from '@mui/icons-material/LocalMovies';
import { Avatar, Box, Container, IconButton, InputAdornment, TextField, Typography, Button } from '@mui/material';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import PersonIcon from '@mui/icons-material/Person';
import LockIcon from '@mui/icons-material/Lock';


function Login() {
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
        <Box className="l-wrapper">
            <Typography variant="h2" component="div" className="text-center p-30">
                ĐẶT VÉ NGAY HÔM NAY
            </Typography>

            <Container maxWidth="sm" className="l-center p-30">
                <Box className="l-box">
                    <Avatar className="l-icon" sx={{ width: 50, height: 50 }}>
                        <LocalMoviesIcon />
                    </Avatar>

                    <Typography variant="h6" component="div" gutterBottom color="black">
                        ĐĂNG NHẬP
                    </Typography>

                    <Box component="form" className="l-form" noValidate sx={{ '& .MuiTextField-root': { mt:4, width: '40ch' } }}>
                        <div>
                            <TextField required label="Tên đăng nhập" id="username" name="username" placeholder="Tên đăng nhập"
                                autoFocus InputProps={{ startAdornment: (
                                    <InputAdornment position="start">
                                        <PersonIcon />
                                    </InputAdornment>
                                )}} />
                        </div>

                        <div>
                            <TextField required label="Mật khẩu" id="password" name="password"  placeholder="Mật khẩu"
                                type={showPassword['password'] ? 'text' : 'password'} InputProps={{
                                    startAdornment: (
                                        <InputAdornment position="start">
                                            <LockIcon />
                                        </InputAdornment>
                                    ),
                                    endAdornment: (
                                    <InputAdornment position="end">
                                        <IconButton edge="end" onClick={() => handleClickShowPassword('password')} onMouseDown={handleMouseDownPassword} className="btn-scale" >
                                            {showPassword['password'] ? <VisibilityOff /> : <Visibility />}
                                        </IconButton>
                                    </InputAdornment>
                                    ),
                                }} />
                        </div>

                        <Button type="submit" fullWidth variant="contained" className="l-btn">
                            Đăng nhập
                        </Button>

                        <Link to="/sign-up">
                            <Button fullWidth variant="contained" className="l-btn btn-sign-up btn-mt">
                                Đăng ký
                            </Button>
                        </Link>
                    </Box>
                </Box>
            </Container>
        </Box>
        </>
    );
};

export default Login;