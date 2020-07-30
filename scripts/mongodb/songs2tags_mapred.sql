var datas = db.raw_songs.aggregate(
		 [
			 { $group : { _id : "$id", tags: { $addToSet: "$tag" }, name:{$first: "$name"}, singers: {$first: "$artists"} } }
		 ]
	)

