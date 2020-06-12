public class Data {
    ItemQueue item;
    Stage stage;

    public Data(ItemQueue it, Stage s){
        item = it;
        stage = s;
    }

    public ItemQueue getQueue() {
        return item;
    }

    public Stage getStage(){
        return stage;
    }
}
