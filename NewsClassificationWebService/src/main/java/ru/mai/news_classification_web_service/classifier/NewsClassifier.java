package ru.mai.news_classification_web_service.classifier;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import ru.mai.news_classification_web_service.classifier.entities.NewsCategory;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class NewsClassifier {
    private Classifier classifier;
    private ArrayList<String> dictionary;

    public NewsClassifier() throws Exception {
        classifier = (Classifier) SerializationHelper.read(new FileInputStream("data/Yandex_News_Classification.model"));
        loadDictionary();
    }

    public NewsCategory classifyText(String text) throws Exception {
        double[] vector = createVector(createWordsArray(text));
        Instances dataModel = createDataModel(vector);

        return NewsCategory.values()[(int)classifier.classifyInstance(dataModel.firstInstance())];
    }
    
    private void loadDictionary() throws Exception  {
        dictionary = new ArrayList<>();
        addFileDataToDict(dictionary, new BufferedReader(new InputStreamReader(new FileInputStream("data/Yandex_News_Dictionary.txt"), StandardCharsets.UTF_8)));
    }

    private void addFileDataToDict(ArrayList<String> dictionary, BufferedReader dictReader) throws Exception {
        String line = dictReader.readLine();
        while (line != null) {
            line = line.toLowerCase().replaceAll("[.,/#!?$%^&*;:{}=_`~()]", "");
            if (!line.isEmpty()) {
                dictionary.add(line);
            }
            line = dictReader.readLine();
        }
    }
    
    private ArrayList<String> createWordsArray(String text) {
        String[] rawWords = text.split("\\s+");

        ArrayList<String> words = new ArrayList<>();
        for (String word : rawWords) {
            word = word.toLowerCase().replaceAll("[.,/#!?$%^&*;:{}=_`~()]", "");
            if (!word.isEmpty()) {
                words.add(word);
            }
        }
        return words;
    }
    
    private double[] createVector(ArrayList<String> words) {
        double[] valueVector = new double[dictionary.size() + 1];
        Arrays.fill(valueVector, 0);

        for (String word : words) {
            int wordIndex = dictionary.indexOf(word);
            if (wordIndex != -1) {
                valueVector[wordIndex] = valueVector[wordIndex] + 1;
            }
        }
        return valueVector;
    }

    private ArrayList<Attribute> createAttributes() {
        ArrayList<String> textClasses = new ArrayList<>();
        for (NewsCategory category : NewsCategory.values()) {
            textClasses.add(category.getCategory());
        }

        ArrayList<Attribute> attributeNames = new ArrayList<>();
        for (int i = 0; i < dictionary.size(); ++i) {
            attributeNames.add(new Attribute("word" + (i + 1)));
        }

        Attribute classAttribute = new Attribute("category", textClasses);
        attributeNames.add(classAttribute);

        return attributeNames;
    }

    private Instances createDataModel(double[] vector) {
        ArrayList<Attribute> attributes = createAttributes();

        Instances dataModel = new Instances("text", attributes, 0);
        dataModel.setClass(attributes.get(attributes.size() - 1));
        dataModel.add(new DenseInstance(1.0, vector));
        dataModel.instance(0).setClassMissing();

        return dataModel;
    }
}
