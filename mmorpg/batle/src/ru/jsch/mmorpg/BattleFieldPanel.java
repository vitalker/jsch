
/*
 * Author: Vitaly Ligay <vitaly.ligay@gmail.com>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * IN THE SOFTWARE.
 */
package ru.jsch.mmorpg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JPanel;

/**
 *
 * @author vit
 */
public class BattleFieldPanel extends JPanel {

    private int cells = 20;
    private int cellWidth = 30;

    private Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Рисуем пользователей
        for (User user : users.values()) {
            g.setColor(user.getColor());
            int x = user.getX() * cellWidth;
            int y = user.getY() * cellWidth;

            int width = cellWidth;
            int level = user.getBlowLevel();
            if (level != 0) {
//                width *= level;
//                user.setBlowLevel(level++);
//                g.setColor(Color.red);
//                g.fillRect(x-width, y-width, 2*width, 2*width);
                String lastCommand = user.getLastCommand();
                if (lastCommand == null) {
                    System.out.println("unknown direction");
                } else {
                    g.setColor(user.getColor());
                    int laserWidth = 10;
                    int power = 10;
                    int length = power * width;
                    int centerX = x + width / 2;
                    int centerY = y + width / 2;
                    switch (lastCommand) {
                        case "w":
                            g.fillRect(centerX, centerY, laserWidth, -length);
                            break;
                        case "a":
                            g.fillRect(centerX, centerY, -length, laserWidth);
                            break;
                        case "s":
                            g.fillRect(centerX, centerY, laserWidth, length);
                            break;
                        case "d":
                            g.fillRect(centerX, centerY, length, laserWidth);
                            break;
                    }
                }
            }
//            if (level == cells*cellWidth) {
//                user.setBlowLevel(0);
//            }
            g.fillRect(x, y, width, width);
            g.setColor(Color.black);

            g.drawString(user.getName(), x + width / 4, y + width / 2);

            if (level != 0) {
//                user.setBlowLevel(--level)
//                g.fillOval(x, y, 1000, 1000);
            }
        }
        // Рисуем поле
        g.setColor(Color.GRAY);
        for (int i = 0; i < cells; ++i) {
            g.drawLine(0, i * cellWidth, getWidth(), i * cellWidth);
            g.drawLine(i * cellWidth, 0, i * cellWidth, getHeight());
        }
    }

    public int getCells() {
        return cells;
    }

    public void setCells(int cells) {
        this.cells = cells;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(cellWidth * cells, cellWidth * cells);
    }

    /**
     * Добавить пользователя
     *
     * @param user
     */
    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    /**
     * Добавить пользователя
     *
     * @param user
     */
    public void removeUser(User user) {
        users.remove(user.getName());
        repaint();
    }

    /**
     * Подвинуть пользователя
     *
     * @param user
     * @param shiftX
     * @param shiftY
     */
    public void shift(User user, int shiftX, int shiftY) {
        // % - чтобы идти по кругу
        int x = (user.getX() + shiftX);
        if (x < 0) {
            x = cells - 1;
        }

        int y = user.getY() + shiftY;
        if (y < 0) {
            y = cells - 1;
        }

        x %= cells;
        y %= cells;

        user.setY(y);
        user.setX(x);
        // Запросить перерисовку
        repaint();
    }

    public void fire(User user) {
        user.setBlowLevel(1);
        repaint();
    }

}
