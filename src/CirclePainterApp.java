import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

/**
 * Diese GraphicsApp zeichnet Kreise an allen Positionen ein, die die
 * Nutzer*innen per Mausklick ausgewählt haben, dazu:
 * <p>
 * - verfügt die App über ein Array zum Speichern von Kreisen
 * - zeichnet alle Einträge dieses Arrays bei jedem Aufruf der draw-Methode
 * - "verlängert" das Array bei jedem Mausklick um eine weitere Stelle
 * - und speichert in dieser einen neuen Kreis an der Stelle, die durch
 * die Mauskoordinaten angegeben worden ist.
 */

public class CirclePainterApp extends GraphicsApp {

    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = 500;
    private static final Color CANVAS_BACKGROUND = Colors.WHITE;
    private static final int CIRCLE_RADIUS = 5;
    private static final Color CIRCLE_COLOR = Colors.RED;

    // Array mit allen Kreisen
    private Circle[] currentCircles;

    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        // Zu Beginn wir das Array mit der Länge 0 initialisiert (es passen keine Kreise rein!)
        currentCircles = new Circle[0];
    }

    @Override
    public void draw() {
        drawBackground(CANVAS_BACKGROUND);
        // Hier werden alle Kreise aus dem currentCircles-Array gezeichnet
        for (Circle circle : currentCircles) {
            circle.draw();
        }
    }

    /**
     * Diese Methode wird automatisch aufgerufen, wenn eine Taste auf der Tastatur heruntergedrückt wurde:
     * Bei jedem Mausklick wird ein neuer Kreis an der Position des Mauszeigers erstellt
     */
    @Override
    public void onMousePressed(MousePressedEvent event) {
        // Wir lesen die Koordinaten des Mauszeigers aus und nutzen diese als Parameter für die addNewCircle-Methode
        addNewCircle(event.getXPos(), event.getYPos());
    }

    /**
     * Diese Methode erstellt einen neuen Kreis an der Position, die über die Parameter x und y angegeben sind und
     * speicher diesen in dem Array currentCircles. Das Array wird dazu "erweiter", in dem ein neues, längeres Array
     * erstellt und die alten Einträge in dieses kopiert werden.
     */
    private void addNewCircle(int x, int y) {
        // Wir erstellen ein neues Array das genau eine Stelle länger als das ältere ist ...
        Circle[] newCircleArray = new Circle[currentCircles.length + 1];
        // ... kopieren alle Werte aus dem älteren Array in das neue ...
        System.arraycopy(currentCircles, 0, newCircleArray, 0, currentCircles.length);
        // ... speichern das neue Array in der existierenden Variable ...
        currentCircles = newCircleArray;
        // ... und speichern in der letzten, freien Stelle des Arrays einen neuen Kreis an der Mausposition
        currentCircles[currentCircles.length - 1] = new Circle(x, y, CIRCLE_RADIUS, CIRCLE_COLOR);
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }

}
