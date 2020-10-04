Booking findBestBooking(Request request, Driver[] drivers) {
        if (drivers.length == 0)
            return null;
        Booking bestBooking = new Booking(drivers[0], request);
        for (int i = 1; i < drivers.length; i++) {
            Booking booking = new Booking(drivers[i], request);
            if (bestBooking.compareTo(booking) > 0)
                bestBooking = booking;
        }

        return bestBooking;
    }