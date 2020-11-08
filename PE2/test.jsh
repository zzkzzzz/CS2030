 Function<List<Item>, List<Item>> takeSword = (x) -> {
        if (x.stream().anyMatch(item -> item instanceof Sword)) {
            return x.stream().map((item) -> {
                if (item instanceof Sword) {
                    Sword sword = (Sword) item;
                    if (sword.isTaken())
                        System.out.println("--> You already have sword.");
                    else {
                        item = sword.take();
                        System.out.println("--> You have taken sword.");
                    }
                }
                return item;
            }).collect(Collectors.toList());
        } else {
            System.out.println("--> There is no sword.");
            return x.stream().collect(Collectors.toList());
        }
    };

  Function<List<Item>, List<Item>> killTroll = (x) -> {
        if (x.stream().anyMatch(item -> item instanceof Troll)) {
            if (x.stream().anyMatch(item -> item instanceof Sword)) {
                Sword sword = (Sword) x.stream().filter(item -> item instanceof Sword).collect(Collectors.toList())
                        .get(0);
                if (sword.isTaken()) {
                    System.out.println("--> Troll is killed.");
                    return x.stream().filter((item) -> !(item instanceof Troll)).collect(Collectors.toList());
                } else {
                    System.out.println("--> You have no sword.");
                }
            } else {
                System.out.println("--> You have no sword.");
            }
        } else {
            System.out.println("--> There is no troll.");
        }
        return x.stream().collect(Collectors.toList());
    };