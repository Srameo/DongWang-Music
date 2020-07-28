<template>
    <section class="breadcumb-area bg-img bg-overlay">
        <div class="bradcumbContent">
            <p>{{id}}</p>
            <h2>{{musicname}}</h2>
        </div>
    </section>
</template>

<script>
export default {
    props: ['musicname', 'id']
    
}
</script>

<style scoped>
.bg-overlay {
  position: relative;
  z-index: 2;
  background-position: center center;
  background-image: url(../../assets/bg-img/breadcumb.jpg);
  background-size: cover; }
  .bg-overlay::after {
    background-color: rgba(0, 0, 0, 0.65);
    position: absolute;
    z-index: -1;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    content: ""; }
</style>