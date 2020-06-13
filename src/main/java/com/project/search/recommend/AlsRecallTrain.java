package com.project.search.recommend;


import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.IOException;
import java.io.Serializable;

//ALS Recall Training
public class AlsRecallTrain implements Serializable {



    public static void main(String[] args) throws IOException {
        //Initializing Spark Environment
        SparkSession spark = SparkSession.builder().master("local").appName("SearchApp").getOrCreate();

        JavaRDD<String> csvFile = spark.read().textFile("file:///Users/fanyujia/Desktop/data/behavior.csv").toJavaRDD();

        JavaRDD<Rating> ratingJavaRDD = csvFile.map(new Function<String, Rating>() {
            @Override
            public Rating call(String v1) throws Exception {
                return Rating.parseRating(v1);
            }
        });

        Dataset<Row> rating = spark.createDataFrame(ratingJavaRDD,Rating.class);

        //Split data
        Dataset<Row>[] splits = rating.randomSplit(new double[]{0.8,0.2});

        Dataset<Row> trainingData = splits[0];
        Dataset<Row> testingData = splits[1];


        ALS als = new ALS().setMaxIter(10).setRank(5).setRegParam(0.01).
                setUserCol("userId").setItemCol("shopId").setRatingCol("rating");

        //Training
        ALSModel alsModel = als.fit(trainingData);



        //Evaluation
        Dataset<Row> predictions = alsModel.transform(testingData);

        //rmse
        RegressionEvaluator evaluator = new RegressionEvaluator().setMetricName("rmse")
                .setLabelCol("rating").setPredictionCol("prediction");
        double rmse = evaluator.evaluate(predictions);
        System.out.println("rmse = "+rmse);

        alsModel.save("file:///Users/fanyujia/Desktop/data/alsmodel");


    }



    public static class Rating implements Serializable{
        private int userId;
        private int shopId;
        private int rating;

        public static Rating parseRating(String str){
            str = str.replace("\"","");
            String[] strArr = str.split(",");
            int userId = Integer.parseInt(strArr[0]);
            int shopId = Integer.parseInt(strArr[1]);
            int rating = Integer.parseInt(strArr[2]);

            return new Rating(userId,shopId,rating);
        }

        public Rating(int userId, int shopId, int rating) {
            this.userId = userId;
            this.shopId = shopId;
            this.rating = rating;
        }

        public int getUserId() {
            return userId;
        }

        public int getShopId() {
            return shopId;
        }

        public int getRating() {
            return rating;
        }
    }
}

