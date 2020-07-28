<template>
    <div>
        <top :musicname="'憨憨'" :id=1></top>
        <mid :id="id"></mid>
    </div>
</template>

<script>
import Mid from './music-player/mid'
import Top from './music-player/top'
export default {
    components: {
        Top,
        Mid
    },
    data() {
        return {
            id: 0
        }
    }
}
</script>

<style scoped>

</style>