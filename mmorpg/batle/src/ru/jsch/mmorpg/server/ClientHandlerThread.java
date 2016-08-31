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
package ru.jsch.mmorpg.server;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import ru.jsch.mmorpg.BattleFieldPanel;
import ru.jsch.mmorpg.User;

/**
 *
 * @author vit
 */
public class ClientHandlerThread extends Thread {

    private Socket clientSocket;
    private final BattleFieldPanel battleFieldPanel;
    private final User user;

    public ClientHandlerThread(User user, BattleFieldPanel battleFieldPanel, Socket socket) {
        this.clientSocket = socket;
        this.battleFieldPanel = battleFieldPanel;
        this.user = user;
    }

    @Override
    public void run() {
        try {
            clientSocket.setKeepAlive(true);
            clientSocket.setSoTimeout(100000);
            //
            try (InputStream in = clientSocket.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("c=")) {
                        String color = line.substring(2);
                        int hex = Integer.parseInt(color, 16);
                        user.setColor(new Color(hex));
                        battleFieldPanel.repaint();
                    } else if (line.startsWith("n=")) {
                        user.setName(line.substring(2));
                        battleFieldPanel.repaint();
                    }
                    
                    switch (line) {
                        case "w":
                            battleFieldPanel.shift(user, 0, -1);
                            user.setLastCommand(line);
                            break;
                        case "a":
                            battleFieldPanel.shift(user, -1, 0);
                            user.setLastCommand(line);
                            break;
                        case "s":
                            battleFieldPanel.shift(user, 0, 1);
                            user.setLastCommand(line);
                            break;
                        case "d":
                            battleFieldPanel.shift(user, 1, 0);
                            user.setLastCommand(line);
                            break;
                        case "f":
                            battleFieldPanel.fire(user);
                            break;
                    }
                }
            } finally {
                System.out.println("Удаление пользователя [" + user.getName() + "] с поля");
                battleFieldPanel.removeUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
