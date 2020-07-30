db.raw_songs.mapReduce(
function() {
	for(var artist in this.artists) {
		emit(artist.id, artist)
	}
}, 
function(key, value) {
	return value[0]
}, 
{
out: "singers"
})