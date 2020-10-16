Logger<Integer> add(Logger<Integer> a, int b) {
        return a.map(x -> x + b);
    }

 Logger<Integer> sum(int n) {
        if (n == 0) {
            return Logger.make(0);
        } else {
            return add(sum(n - 1), n);
        }

    }

   Logger<Integer> f(int n) {
        if (n == 1) {
            return Logger.make(1);
        } else if (n % 2 == 0) {
            return Logger.make(n).flatMap(x -> Logger.make(x).map(y -> y / 2)).flatMap(z -> f(z));
        } else {
            return Logger.make(n).flatMap(x -> Logger.make(x).map(y -> 3 * y).map(z -> z + 1)).flatMap(k -> f(k));
        }
    }
