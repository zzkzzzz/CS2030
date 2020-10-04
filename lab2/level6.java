package lab2;

import java.util.ArrayList;

public class level6 {
    public void serveCruises(Cruise[] cruiseArray) {
        // arraylist to store all loaders
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
                    loaders.set(loaderStatus, existingLoader);
                    System.out.println(existingLoader);
                }
                // new loader
                else {
                    idOfLoaders++;
                    Loader loader;
                    // it should be a recycle loader if its id is divisible by 3
                    if (idOfLoaders % 3 == 0) {
                        loader = new RecycledLoader(idOfLoaders, cruiseArray[i]);
                    } else {
                        loader = new Loader(idOfLoaders, cruiseArray[i]);
                    }

                    loaders.add(loader);
                    System.out.println(loader);

                }
            }

        }
    }

    // check existing loaders status
    // if its available, then return its index
    // else return -1, means no loader available
    private int loaderStatus(ArrayList<Loader> loaders, Cruise cruise) {
        for (int q = 0; q < loaders.size(); q++) {
            if (loaders.get(q).canServe(cruise)) {
                return q;
            }
        }
        return -1;
    }

}
