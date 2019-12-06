public class GameType {

    GameType(GameInfo gameInfo) {
        Tank tank1 = new FirstTank(180, gameInfo.isTest ? 750 : 10, gameInfo.isTest ? 668 : 10, gameInfo);
        Tank tank2 = new SecondTank(0, 924, 668, gameInfo);

        gameInfo.allTanks.add(tank1);
        gameInfo.allTanks.add(tank2);
    }
}
