 void serveCruises(Cruise[] cruiseArray) {
        ArrayList<Loader> loaders = new ArrayList<Loader>();
        int numsOfLoaders = 0;
        int idOfLoaders = 0;
        for (int i = 0; i < cruiseArray.length; i++) {
            numsOfLoaders = cruiseArray[i].getNumOfLoadersRequired();

            for (int q = 1; q <= numsOfLoaders; q++) {
                // if got exisitng loader avaiable
                int loaderStatus = loaderStatus(loaders, cruiseArray[i]);
                if (loaderStatus != -1) {
                    Loader existingLoader = loaders.get(loaderStatus).serve(cruiseArray[i]);
                    System.out.println(existingLoader);
                    loaders.set(loaderStatus, existingLoader);
                }
                // new loader
                else {
                    idOfLoaders++;
                    Loader loader = new Loader(idOfLoaders, cruiseArray[i]);
                    loaders.add(loader);
                    System.out.println(loader);

                }
            }

        }
    }

    int loaderStatus(ArrayList<Loader> loaders, Cruise cruise) {
        for (int q = 0; q < loaders.size(); q++) {
            if (loaders.get(q).canServe(cruise)) {
                return q;
            }
        }
        return -1;
    }