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
package ru.jsch.mmorpg.client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author vit
 */
class RemoteControl extends KeyAdapter {

    private final OutputStream out;

    public RemoteControl() throws IOException {
        Socket socket = new Socket("localhost", 8888);
        out = socket.getOutputStream();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    out.write('w');
                    out.flush();
                    break;
                case KeyEvent.VK_DOWN:
                    out.write('s');
                    out.flush();
                    break;
                case KeyEvent.VK_LEFT:
                    out.write('a');
                    out.flush();
                    break;
                case KeyEvent.VK_RIGHT:
                    out.write('d');
                    out.flush();
                    break;

                case ' ':
                    System.out.println("FIRREEE!");
                    out.write('f');
                    out.flush();
                    break;
            }
            out.write('\r');
            out.write('\n');
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
