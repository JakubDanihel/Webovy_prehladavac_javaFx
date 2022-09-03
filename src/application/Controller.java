package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class Controller implements Initializable{
	
	@FXML
	private WebView webView;
	
	@FXML
	private TextField textField;
	
	private WebEngine engine;
	
	//vytvorenie home page
	private String homePage;
	
	//hodnota pre zmenu priblizenia
	private double webZoom;
	
	//kladanie historie navstivenych stran
	private WebHistory historia;

	//spustenie programu po otvoreni okna
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		engine = webView.getEngine();
		homePage = "www.google.com";
		textField.setText(homePage);
		
		//nastavenie pociatocnej hodnoty pre zoom
		webZoom = 1;
		
		loadpage();
		
	}
	
	
	//nacitanie stany
	public void loadpage() {
		//engine.load("https://www.google.com/");
		engine.load("https://"+textField.getText());
	}
	
	//znovunacitanie srany
	public void refreshPage() {
		engine.reload();
	}
	
	//priblizenie strany
	public void zoomIn() {
		
		webZoom += 0.25;
		webView.setZoom(webZoom);
	}
	
	//oddialenie strany
	public void zoomOut() {
		
		webZoom -= 0.25;
		webView.setZoom(webZoom);
	}

	//zobrazenie historie prehladavania
	public void zobrazHistoriu() {
		
		//ziskanie hodnot z historie
		historia = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = historia.getEntries();
		
		//vypisanie navstivenych stranok
		for(WebHistory.Entry entry : entries) {
			//System.out.println(entry);
			System.out.println(entry.getUrl() +" "+ entry.getLastVisitedDate());
		}
	}
	
	
	//metoda pre vratenie stranku dozadu z historie
	public void spat() {
		historia = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = historia.getEntries();
		
		//posunutie sa v histori o jednu stanku spat
		historia.go(-1);
		
		//vypisanie sucasnej url
		textField.setText(entries.get(historia.getCurrentIndex()).getUrl());
	}
	
	//metoda pre ist na nasledujucu stranku z historie
	public void dopredu() {
		historia = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = historia.getEntries();
		
		//posunutie sa v histori o jednu stranku dalej
		historia.go(+1);
		
		//vypisanie sucasnej url
		textField.setText(entries.get(historia.getCurrentIndex()).getUrl());
	}
	
}
