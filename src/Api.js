import axios from "axios";

const HOST ="http://localhost:8080/cinema/";

export const endpoints = {
    'log-in': '/auth/token',
    'sign-up': '/users', 
    'current-user': '/users/my-info',
    'movies': '/movies',
    'schedules': '/show-schedules',
    'seats': '/seats',
    'items': '/items',
    'promotions': '/promotions',
    'create-tickets': '/tickets',
    'create-bill': '/bills',
}

export const auThApi = (accessToken)=> axios.create({
        baseURL:HOST,
        headers:{
            Authorization: `Bearer ${accessToken}`
        }
    })

export default axios.create({
    baseURL : HOST
})