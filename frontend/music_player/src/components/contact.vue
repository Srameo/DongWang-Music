<template>
<div>
    <top></top>
    <mid></mid>
    <foot></foot>
</div>
</template>

<script>
import Top from './contact/top'
import Mid from './contact/mid'
import Foot from './contact/foot'

export default {
    name:'contact',
    components:{
        Top,
        Mid,
        Foot
    }
    
}
</script>

<style scoped>

</style>