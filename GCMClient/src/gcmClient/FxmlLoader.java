package gcmClient;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxmlLoader {

	private Pane view;

	public Pane getPage(String fileName) {
		
		try {
		URL fileUrl = FXMain.class.getResource("/" + fileName + ".fxml");
		//   /GCMClient/src/main/resource/HomeScreen.fxml
		if(fileUrl == null) {
			throw new java.io.FileNotFoundException("FXML File can't be found");
		}

		
			view = new FXMLLoader().load(fileUrl);
			System.out.println("Fxmlloader: " + view.toString());
			System.out.println(fileUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return view;


	}

	private Object FxmlLoader() {
		// TODO Auto-generated method stub
		return null;
	}

}