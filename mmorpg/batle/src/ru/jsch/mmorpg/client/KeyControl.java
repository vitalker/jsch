package ru.jsch.mmorpg.client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import ru.jsch.mmorpg.BattleFieldPanel;
import ru.jsch.mmorpg.User;

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
/**
 *
 * @author vit
 */
public class KeyControl extends KeyAdapter {

    private User user;
    private BattleFieldPanel panel;

    public KeyControl(User user, BattleFieldPanel panel) {
        this.user = user;
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("up");
                panel.shift(user, 0, -1);
                panel.repaint();

                break;

            case KeyEvent.VK_DOWN:
                panel.shift(user, 0, 1);
                System.out.println("down");
                panel.repaint();
                break;

            case KeyEvent.VK_LEFT:
                panel.shift(user, -1, 0);
                System.out.println("left");
                panel.repaint();
                break;

            case KeyEvent.VK_RIGHT:
                panel.shift(user, 1, 0);
                System.out.println("right");
                panel.repaint();
                break;
        }
    }
}
