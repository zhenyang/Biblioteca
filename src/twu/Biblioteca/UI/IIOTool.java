package twu.Biblioteca.UI;

import java.io.IOException;

public interface IIOTool {
    public void output(String message);
    public String getInput() throws IOException;
}
