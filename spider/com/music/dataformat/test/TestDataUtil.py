from com.music.dataformat.util.data_util import DataUtil

if __name__ == '__main__':
    du = DataUtil()
    for i in du.getSongs():
        print(i)
        du.insertSong(i)
    for i in du.getAllSingers():
        print(i)
        for doc in i['singers']:
            print(doc)
            du.insertSinger(doc)