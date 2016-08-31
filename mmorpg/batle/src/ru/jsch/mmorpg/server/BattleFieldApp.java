package ru.jsch.mmorpg.server;

import java.awt.Color;
import java.awt.Container;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import ru.jsch.mmorpg.BattleFieldPanel;
import ru.jsch.mmorpg.User;
import ru.jsch.mmorpg.client.KeyControl;

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
 * Серверное приложение
 * 
 * @author vit
 */
public class BattleFieldApp {

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("battle field");
        Container cp = frame.getContentPane();
        BattleFieldPanel panel = new BattleFieldPanel();
        cp.add(panel);

        User boss = new User();
        boss.setX(10);
        boss.setY(10);
        panel.addUser(boss);
//        
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        panel.addKeyListener(new KeyControl(boss, panel));

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // Открываем сокет на 8888 порту
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            for (int i = 0; i < 1000; ++i) {
                final Socket clientSocket = serverSocket.accept();
                // Запускаем новую нить 
                // panel.addUser(user);
                User user = new User();
                user.setColor(new Color(user.hashCode()));
                panel.addUser(user);
                ClientHandlerThread thread = new ClientHandlerThread(user, panel, clientSocket);
                thread.start();               
                
            }
        }
    }

}
