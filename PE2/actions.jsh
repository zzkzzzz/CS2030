Item takeSword(){
    if(this instanceof Sword)
        if(this.getStatusList().get(this.getStatus())=="You have taken sword.")
            return new Sowrd(0,new ArrayList<String>(Arrays.asList("You already have sword.")));
        else
            return new Sowrd(0,new ArrayList<String>(Arrays.asList("You have taken sword.")));
    else
        return new Sowrd(0,new ArrayList<String>(Arrays.asList("There is no sword.")));
}




Item killTroll(){
    if(this instanceof Troll)


        return new Troll(0,new ArrayList<String>(Arrays.asList("Troll is killed.
                        ")));

    else
        return new Troll(0,new ArrayList<String>(Arrays.asList("There is no troll. ")));


}
