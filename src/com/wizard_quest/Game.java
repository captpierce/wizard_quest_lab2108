package com.wizard_quest;

public class Game
{
    private Parser parser;
    private Room currentRoom;

    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * создаем локации, лепим выходы
     */
    private void createRooms()
    {
        Room garden, hall, cellar, attic, laboratory;
      
        // create the rooms
        garden = new Room("сад у башни волшебника");
        hall = new Room("гостиная в башне");
        cellar = new Room("подвал башни");
        attic = new Room("чердак башни");
        laboratory = new Room("мастерская волшебника");
        
        // initialise room exits
        garden.setExit("восток", hall);
        garden.setExit("юг", attic);
        garden.setExit("запад", cellar);

        hall.setExit("запад", garden);

        cellar.setExit("восток", garden);

        attic.setExit("север", garden);
        attic.setExit("восток", laboratory);

        laboratory.setExit("запак=д", attic);

        currentRoom = garden;  //начинаем из сада
    }

    /**
     *  главный цикл
     */
    public void play() 
    {            
        printWelcome();

        // проверка на выход
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Спасибо за игру. До свидания.");
    }

    /**
     * Открывашка
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Добро пожаловать в квест!");
        System.out.println("Он не готов");
        System.out.println("напечатайте 'помощь', если вам нужна помощь");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("Не знаю, о чем вы...");
            return false;
        }

        if (commandWord == CommandWord.ПОМОЩЬ) {
            printHelp();
        }
        else if (commandWord == CommandWord.ИДТИ) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.ВЫХОД) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("Вы потерялись. Вы одни. Вы бродите");
        System.out.println("в башне волшебника.");
        System.out.println();
        System.out.println("Ваши команды");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // куда?
            System.out.println("Пойти куда?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Здесь нет двери!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }


    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Выйти куда?");
            return false;
        }
        else {
            return true;  // выйти-выйти
        }
    }
}
