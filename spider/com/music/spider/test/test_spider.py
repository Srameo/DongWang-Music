from com.music.spider.util.spider_util import SpiderUtil

import requests
import json

def test_getSongs():
    search_url = SpiderUtil.search_url % ("世界音乐", 1)
    req = requests.get(search_url, headers={"User-Agent": "Mozilla/5.0"})
    j = json.loads(req.text)
    print(j['result']['songs'])
    print(type(j))

if __name__ == "__main__":
    su = SpiderUtil()
    classes = su.getClasses()
    su.getSongs()