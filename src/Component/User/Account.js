import React from 'react';
import './Style.css';
import { Avatar, Box, Button, Container, Divider, Grid, TextField, Typography } from '@mui/material';

function Account() {

    return (
        <>
        <Container maxWidth="md">
            <Typography variant="h4" component="div" className="text-center p-30">
                TÀI KHOẢN
            </Typography>

            <Box className="a-container">
                <Grid container spacing={7}>
                    <Grid item>
                        <Avatar alt="Remy Sharp" sx={{ width: 115, height: 115 }} src="" />
                    </Grid>

                    <Grid item>
                        <Grid container spacing={1.5} direction="column">
                            <Grid item>
                                <Typography variant="h6" component="div" className="text-main">
                                    HỌ và tên
                                </Typography>
                            </Grid>

                            <Grid item>
                                <Grid container spacing={7}>
                                    <Grid item>
                                        <Typography variant="body1" component="div" className="text-des">
                                            Tên đăng nhập:
                                        </Typography>
                                    </Grid>

                                    <Grid item>
                                        <Typography variant="body1" component="div" className="text-des">
                                            Ngày đăng ký:
                                        </Typography>
                                    </Grid>
                                </Grid>
                            </Grid>

                            <Grid item>
                                <Button variant="contained" className="btn-bg">
                                    ĐĂNG XUẤT
                                </Button>
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>

                <Divider className="divider" />

                <Box component="form" className="a-form" sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', '& .MuiTextField-root': { mt: 4, width: '100%' } }} noValidate autoComplete="off">
                    <Grid container spacing={5}>
                        <Grid item xs={6}>
                            <div>
                                <TextField required label="Họ" id="first-name" name="first-name" value="Họ" />
                            </div>
                        </Grid>

                        <Grid item xs={6}>
                            <div>
                                <TextField required label="Tên đệm và tên" id="last-name" name="last-name" value="Tên" />
                            </div>
                        </Grid>
                    </Grid>

                    <div className="inner-width">
                        <TextField disabled id="email-disabled" label="Email" value="email" />

                        <TextField disabled id="password-disabled" name="password" label="Mật khẩu" defaultValue="123" />

                        <TextField required  label="Số điện thoại" id="phone-number" name="phone-number" value="số" />
                    </div>

                    <Button type="submit" variant="contained" className="btn-bg" sx={{ mt: 4 }}>
                        Cập nhật
                    </Button>
                </Box>
            </Box>
        </Container>
        </>
    );
};

export default Account;