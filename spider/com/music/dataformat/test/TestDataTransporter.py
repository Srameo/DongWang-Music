from com.music.dataformat.util.data_transporter import DataTransporter
from com.music.dataformat.util.data_util import DataUtil

if __name__ == '__main__':
    dt = DataTransporter()
    du = DataUtil()
    for doc in du.getAllSingers():
        song_id = doc['_id']
        dt.insertSong(song_id, doc['name'])
        for style in doc['tags']:
            dt.insertSongAndStyle(song_id, style)
        for singer in doc['singers']:
            dt.insertSongAndSinger(singer['id'], song_id)
            if singer['id'] == 0:
                break
    dt.close()