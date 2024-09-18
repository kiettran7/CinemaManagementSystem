import React, { createContext, useContext, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useMovieContext } from './MovieContext';

const BuyTicketContext = createContext();

export const BuyTicketContextProvider = ({ children }) => {
    // danh sách các bước
    const steps = [
        'Chọn thời gian và địa điểm',
        'Bước 2',
        'Bước 3',
        'Bước 4'
    ];

    const [ activeStep, setActiveStep ] = useState(0); // bước hiện tại
    const { selectedMovie } = useMovieContext(); // phim đã chọn mua
    const navigate = useNavigate();

    // danh sách ghế tại rạp
    const rowsOfSeat = [
        { row: 'A', seats: 18 }, { row: 'B', seats: 18 }, { row: 'C', seats: 18 }, { row: 'D', seats: 18 }, { row: 'E', seats: 18 },
        { row: 'F', seats: 18 }, { row: 'G', seats: 18 }, { row: 'H', seats: 18 }, { row: 'I', seats: 18 }, { row: 'J', seats: 18 },
        { row: 'K', seats: 12 }
    ];

    // giá niêm yết cho từng loại ghế
    const listPriceSeat = [
        { type: 'normal', price: '60000' },
        { type: 'vip', price: '80000' },
        { type: 'couple', price: '110000' }
    ];

    // các biến để đếm ngược thời gian
    const initialTotalSeconds = 300;
    const [ totalSeconds, setTotalSeconds ] = useState(initialTotalSeconds); // tổng giây
    const [ minutes, setMinutes ] = useState(initialTotalSeconds / 60); // phút
    const [ seconds, setSeconds ] = useState(initialTotalSeconds % 60); // giây
    const [ isActiveTimer, setIsActiveTimer ] = useState(false); // biến để khởi động timer

    const [ selectedSeats, setSelectedSeats ] = useState([]); // danh sách các ghế tick chọn
    const [ totalPriceSeats, setTotalPriceSeats ] = useState({}); // tổng tiền các loại ghế đã chọn

    const [ selectedFoods, setSelectedFoods ] = useState([]); // danh sách các dồ ăn đã chọn
    const [ quantityFoods, setQuantityFoods ] = useState(0); // số lượng đồ ăn chọn theo từng loại
    const [ totalPriceFoods, setTotalPriceFoods ] = useState({}); // tổng tiền các loại đồ ăn đã chọn

    const [ totalPrice, setTotalPrice ] = useState(0); // tổng tiền các bước

    // các sản phẩm đồ ăn đi kèm
    const foodItems = [
        { id: 1, name: 'OL Special Combo1 Bap mam Ga Lac (Sweet)', price: 270000,
            img: 'https://booking.bhdstar.vn/CDN/media/entity/get/ItemGraphic/662036?width=160&height=160&referenceScheme=HeadOffice&allowPlaceHolder=true' },
        { id: 2, name: 'Online Single Combo - Sweet 32 Oz', price: 250000,
            img: 'https://booking.bhdstar.vn/CDN/media/entity/get/ItemGraphic/662036?width=160&height=160&referenceScheme=HeadOffice&allowPlaceHolder=true' }
    ];



    // chuyển sang bước tiếp theo
    const nextStep = (movieId) => {
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
        navigate(`/buy-ticket/step${activeStep + 2}?movieId=${movieId.id}`);
    };


    // lùi về bước trước đó
    const backStep = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
        navigate(`/buy-ticket/step${activeStep}?movieId=${selectedMovie.id}`);
    };    


    // lùi về step1
    const backStep1 = () => {
        setActiveStep(0);
        navigate(`/buy-ticket/step${1}?movieId=${selectedMovie.id}`);
        resetTimer();
        setIsActiveTimer(false);
        resetStep2();
        resetStep3();
        setTotalPrice(0);
    }


    // hook để bắt đầu đếm ngược thời gian
    useEffect(() => {
        if (isActiveTimer) {
            const interval = setInterval(() => {
                if (totalSeconds > 0) {
                    setTotalSeconds(totalSeconds - 1);
                    setMinutes(Math.floor(totalSeconds / 60));
                    setSeconds(totalSeconds % 60);
                } else {
                    clearInterval(interval);
                    handleTimeout();
                };
            }, 1000);

            return () => clearInterval(interval);
        }
    }, [isActiveTimer, totalSeconds]);


    // format lại kiểu hiển thị
    const formatTime = (time) => {
        return `${time.minutes} phút, ${time.seconds < 10 ? '0' : ''}${time.seconds} giây`;
    };


    // xử lý khi hết thời gian đếm ngược
    const handleTimeout = () => {
        alert('Hết thời gian chờ. Vui lòng thực hiện lại bước 1!');
        backStep1();
    };


    // reset lại đồng hồ đếm ngược
    const resetTimer = () => {
        setTotalSeconds(initialTotalSeconds);
        setMinutes(Math.floor(initialTotalSeconds / 60));
        setSeconds(initialTotalSeconds % 60);
    }


    // xử lý sự kiện nhấn chọn ghế
    const handleSelectSeat = (row, seatIndex, seatType) => {
        // biến chứa ghế đã chọn
        const alreadySelected = selectedSeats.find((seat) => seat.row === row && seat.seatIndex === seatIndex);
        let updatedSeats;
        
        if (alreadySelected) {
            // xóa khỏi danh sách
            updatedSeats = selectedSeats.filter((seat) => !(seat.row === row && seat.seatIndex === seatIndex));
        } else {
            // thêm mới nếu ghế chưa được chọn
            const newSeat = { row: row, seatIndex: seatIndex, seatType: seatType };
            updatedSeats = [...selectedSeats, newSeat];
        };

        setSelectedSeats(updatedSeats);
        calculatePriceSeats(updatedSeats, seatType);
    };


    // lấy loại ghế
    const getSeatType = (seatType) => {
        switch (seatType) {
            case 'normal':
                return 'Ghế thường';
            case 'couple':
                return 'Ghế đôi';
            case 'vip':
                return 'Ghế vip';
            default:
                return null;
        }
    };


    // tính tổng tiền từng loại ghế
    const calculatePriceSeats = (seats, seatType) => {
        const seatsOfType = seats.filter((seat) => seat.seatType === seatType);

        const total = seatsOfType.reduce((total, seat) => {
            const priceOfType = listPriceSeat.find((p) => p.type === seat.seatType)?.price || 0;
            return total + parseFloat(priceOfType);
        }, 0);

        setTotalPriceSeats((prevState) => ({
            ...prevState,
            [seatType]: total
        }));
    };


    // reset danh sách ghế đã chọn
    const resetStep2 = () => {
        setSelectedSeats([]);
        setTotalPriceSeats({});
    }


    // xử lý khi tăng số lượng 1 loại đồ ăn
    const handleIncrementFood = (name) => {
        setQuantityFoods((prev) => {
            const increment = (prev[name] || 0) + 1;
            const newQuantity = {
                ...prev,
                [name]: increment
            };
    
            // kiểm tra nếu chưa có thì thêm vào
            if (!selectedFoods.some((item) => item.name === name)) {
                const newItem = foodItems.find((item) => item.name === name);
                setSelectedFoods([...selectedFoods, newItem]);
            }
            calculatePriceFoods(name, increment);
    
            return newQuantity;
        });
    };


    // xử lý khi giảm số lượng 1 loại đồ ăn
    const handleDecrementFood = (name) => {
        setQuantityFoods((prev) => {
            const decrement = Math.max((prev[name] || 0) - 1, 0);
            const newQuantity = {
                ...prev,
                [name]: decrement
            };

            if (newQuantity[name] === 0) {
                setSelectedFoods((prevFoodItems) => {
                    // Kiểm tra nếu món ăn có trong danh sách trước khi xóa
                    if (prevFoodItems.some((item) => item.name === name)) {
                        return prevFoodItems.filter((item) => item.name !== name);
                    }
                    return prevFoodItems;
                });
            }
            calculatePriceFoods(name, decrement);
            return newQuantity;
        });
    }



    // tính tổng tiền các loại đồ ăn đã chọn
    const calculatePriceFoods = (name, quantity) => {
        const priceOfFood = foodItems.find((p) => p.name === name)?.price || 0;
        const total = priceOfFood * quantity;

        setTotalPriceFoods((prev) => ({
            ...prev,
            [name]: total
        }));
    }

    const resetStep3 = () => {
        setSelectedFoods([]);
        setQuantityFoods(0);
        setTotalPriceFoods({});
    }


    // tính tổng tiền
    useEffect(() => {
        const totalStep2 = Object.values(totalPriceSeats).reduce((acc, price) => acc + price, 0);
        const totalStep3 = Object.values(totalPriceFoods).reduce((acc, price) => acc + price, 0);
        setTotalPrice(totalStep2 + totalStep3);
    }, [totalPriceSeats, totalPriceFoods]);

    return (
        <BuyTicketContext.Provider value={{ steps, activeStep, nextStep, backStep, backStep1, minutes, seconds, formatTime, setIsActiveTimer,
            rowsOfSeat, handleSelectSeat, getSeatType, selectedSeats, totalPriceSeats, foodItems, handleIncrementFood,
                handleDecrementFood, selectedFoods, quantityFoods, totalPriceFoods, totalPrice }}>
                    { children }
        </BuyTicketContext.Provider>
    );
};

export const useBuyTicketContext = () => useContext(BuyTicketContext)