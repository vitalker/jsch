package ru.jsch.mmorpg.client;

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

import java.awt.Container;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Клиентское приложение
 * 
 * @author vit
 */
public class ControlApp {


    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("battle field");
        Container cp = frame.getContentPane();
        JPanel panel = new JPanel();

        panel.addKeyListener(new RemoteControl());
        cp.add(panel);
        // Чтобы кнопки нажимались
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        frame.pack();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
