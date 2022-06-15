package org.cuminhtien.kafka;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Customer {

    @CsvBindByPosition(position = 0)
    String id;

    @CsvBindByPosition(position = 1)
    String numOrder;

    @CsvBindByPosition(position = 2)
    String age;

    @CsvBindByPosition(position = 3)
    String Tel;
}
