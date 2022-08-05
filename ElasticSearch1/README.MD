Elastic chạy trên cụm server tại: `172.17.80.25:9200`

Kibana chạy trên cụm server tại: `172.17.80.25:5601`

Query bài 1:

    GET dantri/_search
    {
        "query": {
            "bool" : {
                "should": [
                    {
                        "multi_match" : {
                        "query":  "an toàn",
                        "fields": [ "title", "description", "content"]
                    }
                },
            {
                "multi_match" : {
                    "query":  "đường bộ",
                        "fields": [ "title", "description", "content"]
                    }
                },
            {
                "multi_match" : {
                    "query":  "đường sắt",
                        "fields": [ "title", "description", "content"]
                    }
                },
            {
                "range": {
                    "time": {
                        "gte": 1356998400,
                        "lt": 1388534400
                            }
                        }
                    }
                ]
            }
        }
    }

Query bài 2:

    GET dantri/_search
        {
            "query":{
                "bool": {
                    "must":
                {
                    "prefix": {
                        "title.keyword":{
                            "value": "Hà Nội"
                        }
                    }    
                },
                    "must_not":{
                        "match_phrase_prefix": {
                            "description":{
                                "query": "Hà Nội"
                                }
                            }
                        }
                    }
                },
                "sort" : [
                    { "time" : "desc" }
                ]
            }

Bài 3:

Chạy file GenToken sử dụng pyvi tokenize các từ tiếng việt, sửa tên index tạo file json mới đẩy dữ liệu lên index `suggest_tiencm8` sử dụng câu lệnh

    curl -XPOST "http://172.17.80.25:9200/_bulk?pretty" -H 'Content-Type: application/json' --data-binary @/home/hadoop/tiencm8/output.json

Tạo index bài 3:

    PUT suggest_tiencm8
    {
        "mappings": {
            "properties" : {
                "suggest_title" : {
                    "type" : "completion"
                }
            }
        }
    }

Query bài 3:

        GET /suggest_tiencm8/_search
        {
            "suggest": {
                "": {
                    "prefix": "ha",
                    "completion": {
                        "field": "suggest_title",
                        "size": 30,
                        "fuzzy": {}
                    }
                }
            }
        }

`controller` gọi đến method trong `repository`, `config` khởi tạo kết nối đến cụm elasticsearch

Ví dụ request sử dụng Spring:

    http://localhost:8080/get/{keyword}/{size}






