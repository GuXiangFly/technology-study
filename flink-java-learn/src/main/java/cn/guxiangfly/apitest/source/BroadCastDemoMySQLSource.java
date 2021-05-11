package cn.guxiangfly.apitest.source;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

public class BroadCastDemoMySQLSource extends RichSourceFunction<Tuple2<String, Tuple2<String, Integer>>> {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    boolean flag = true;
    @Override
    public void open(Configuration parameters) throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_study?useUnicode=true&characterEncoding=UTF-8", "root", "root");
        String sqlString = "select * from user_info";
        preparedStatement = connection.prepareStatement(sqlString);
    }

    @Override
    public void run(SourceContext<Tuple2<String, Tuple2<String, Integer>>> ctx) throws Exception {
        while (flag){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String userID = resultSet.getString("user_id");
                String userName = resultSet.getString("user_name");
                Integer userAge = resultSet.getInt("user_age");
                ctx.collect(new Tuple2<>(userID,new Tuple2<>(userName,userAge)));
            }

            Thread.sleep(1000L);
        }
    }

    @Override
    public void close() throws Exception {
        if (preparedStatement!=null){
            preparedStatement.close();
        }
        if (connection != null){
            connection.close();
        }

    }

    @Override
    public void cancel() {
        flag = false;
    }
}