package com.github.mott0.NewsClassifier;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class NewsClassifier {
    private Classifier classifier;
    private ArrayList<String> dictionary;

    public NewsClassifier() throws Exception {
        classifier = (Classifier) SerializationHelper.read(new FileInputStream("Yandex_News_Classification.model"));
        loadDictionary();
    }

    public NewsCategory classifyText(String text) throws Exception {
        double[] vector = createVector(createWordsArray(text));
        ArrayList<Attribute> attributes = createAttributes();
        Instances dataModel = createDataModel(attributes, vector);

        return NewsCategory.values()[(int)classifier.classifyInstance(dataModel.firstInstance())];
    }
    
    private void loadDictionary() throws Exception  {
        dictionary = new ArrayList<>();
        BufferedReader dictionaryReader = new BufferedReader(new InputStreamReader(new FileInputStream("Yandex_News_Dictionary.txt"), "UTF8"));
        String line = dictionaryReader.readLine();
        while (line != null) {
            dictionary.add(line);
            line = dictionaryReader.readLine();
        }
    }
    
    private ArrayList<String> createWordsArray(String text) {
        String[] rawWords = text.split("\\s+");

        ArrayList<String> words = new ArrayList<>();
        for (String word : rawWords) {
            word = word.toLowerCase().replaceAll("[.,\\/#!\\?$%\\^&\\*;:{}=_`~()]", "");
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
            Integer wordIndex = dictionary.indexOf(word);
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
            attributeNames.add(new Attribute("word_" + (i + 1)));
        }

        Attribute classAttribute = new Attribute("category", textClasses);
        attributeNames.add(classAttribute);

        return attributeNames;
    }

    private Instances createDataModel(ArrayList<Attribute> attributes, double[] vector) {
        Instances dataModel = new Instances("text", attributes, 0);
        dataModel.setClass(attributes.get(attributes.size() - 1));
        dataModel.add(new DenseInstance(1.0, vector));
        dataModel.instance(0).setClassMissing();

        return dataModel;
    }
}
