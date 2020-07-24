import pymongo

class DataUtil:
    client = pymongo.MongoClient('mongodb://admin:admin@101.200.189.12:27017/')
    db = client['songs']
    origin_data = db['raw_songs']
    format_data_songs = db['all_songs']
    format_data_singers = db['all_singers']

    def getSongs(self):
        return self.origin_data.aggregate([
            {
                "$group": {
                    "_id": "$id",
                    "tags": {"$addToSet": "$tag"},
                    "name": {"$first": "$name"},
                    "singers": {"$first": "$artists"}
                }
            }
        ])

    def insertSong(self, doc):
        self.format_data_songs.insert_one(doc)

    def getAllSingers(self):
        return self.format_data_songs.find({})

    def getSingers(self):
        return self.format_data_singers.find({})

    def insertSinger(self, doc):
        if self.format_data_singers.find_one({"_id": doc['id']}) is None:
            self.format_data_singers.insert_one({
                "_id": doc['id'],
                "name": doc['name']
            })
