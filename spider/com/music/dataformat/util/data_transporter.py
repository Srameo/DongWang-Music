import pymysql

class DataTransporter:

    mysql = pymysql.connect(host="101.200.189.12", port=3306, user="root", password="root", database="music_player")
    cursor = mysql.cursor()

    def insertSinger(self, id, name):
        name = name.replace("\"", "\'")
        sql = "INSERT INTO singers(id, name, gender, location) VALUES (%d, \"%s\", 1, 'unknown')" % (id, name)
        print(sql)
        self.cursor.execute(sql)

    def insertSong(self, id, name):
        name = name.replace("\"", "\'")
        sql = "INSERT INTO music(id, name, num) VALUES (%d, \"%s\", 0)" % (id, name)
        print(sql)
        self.cursor.execute(sql)

    def insertSongAndSinger(self, sid, mid):
        sql = "INSERT INTO singer_music(sid, mid) VALUES (%d, %d)" % (sid, mid)
        print(sql)
        self.cursor.execute(sql)

    def insertSongAndSinger(self, sid, mid):
        sql = "INSERT INTO singer_music(sid, mid) VALUES (%d, %d)" % (sid, mid)
        print(sql)
        self.cursor.execute(sql)

    def insertSongAndStyle(self, mid, name):
        sql = "SELECT id FROM style WHERE detail LIKE '%" + name + "%'"
        print(sql)
        self.cursor.execute(sql)
        sid = self.cursor.fetchone()
        if sid is None:
            return
        sql = "INSERT INTO music_style(mid, sid) VALUES (%d, %d)" % (mid, sid[0])
        print(sql)
        self.cursor.execute(sql)

    def commit(self):
        self.mysql.commit()

    def close(self):
        self.mysql.commit()
        self.cursor.close()
        self.mysql.close()

