import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        if (args.length > 0) {
            model.setPath(args[1]);
        }
        UI ui = new UI(model);
        ui.show();
    }
}
