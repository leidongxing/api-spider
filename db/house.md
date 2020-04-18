#定义索引房源
PUT /house
{
"settings": {
"number_of_shards": 1,
"number_of_replicas": 1
},
"mappings": {
"properties": {
"id":{"type":"long"},
"link":{"type": "text"},
"district_name":{"type":"keyword"},
"street_name":{"type":"text","analyzer": "ik_max_word","search_analyzer": "ik_smart"},
"community_name":{"type":"text","analyzer": "ik_max_word","search_analyzer": "ik_smart"},
"subway_info":{"type":"text","analyzer": "ik_max_word","search_analyzer": "ik_smart"},
"total_price":{"type":"integer"},
"unit_price":{"type":"integer"},
"house_type":{"type":"text","analyzer": "ik_max_word","search_analyzer": "ik_smart"},
"area":{"type":"double"},
"house_level":{"type":"text","analyzer": "ik_max_word","search_analyzer": "ik_smart"},
"decoration":{"type":"text","analyzer": "ik_max_word","search_analyzer": "ik_smart"},
"forword":{"type":"text","analyzer":"whitespace","fielddata":true}
}
}
}


#测试分词器
GET _analyze?pretty
{
  "analyzer": "ik_smart",
  "text":"高楼层 (共22层)"
}
