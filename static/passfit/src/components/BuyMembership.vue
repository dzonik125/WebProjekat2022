<template>
    <div id="buyMembership" style="height = 100vh;">
        <div class="demo">
        <div class="container">
            <div class="row" style="margin-left:20rem">
                <div class="col-md-4 col-sm-6">
                    <div class="pricingTable">
                        <div class="pricingTable-header">
                            <h3 class="heading">Mesecna</h3>
                            <span class="subtitle">Mesecna PassFit clanarina</span>
                            <div class="price-value">3000
                                <span class="currency"></span>
                                <span class="month">/mes</span>
                            </div>
                        </div>
                        <ul class="pricing-content">
                            <li>3 ulaska dnevno</li>
                            <li>Pristup preko 100 fitness centara sirom zemlje</li>
                            <li>24/7 uzivo podrska</li>
                            <li>Pristup PassFit specijalnim treninzima</li>
                        </ul>
                        <button class="read btn" v-on:mouseleave="hideDiscount(1)" v-on:mouseover="calculateDiscount(1)" v-on:click.prevent="buyMembership($event)" name="Uclani se">Uclani se<i class="fa fa-angle-right"></i></button>
                        <p style="display: none;" name="disc1">Cena sa popustom: {{3000 - (3000 * discount)}}</p>
                    </div>
                </div>

                <div class="col-md-4 col-sm-6">
                    <div class="pricingTable">
                        <div class="pricingTable-header">
                            <h3 class="heading">Godisnja</h3>
                            <span class="subtitle">Godisnja PassFit clanarina</span>
                            <div class="price-value">36000
                                <span class="currency"></span>
                                <span class="month">/mes</span>
                            </div>
                        </div>
                        <ul class="pricing-content">
                            <li>10 ulazaka dnevno</li>
                            <li>Pristup preko 100 fitness centara sirom zemlje</li>
                            <li>24/7 uzivo podrska</li>
                            <li>Pristup PassFit specijalnim treninzima</li>
                        </ul>
                        <button class="read btn" v-on:mouseleave="hideDiscount(2)" v-on:mouseover="calculateDiscount(2)" v-on:click.prevent="buyMembership($event)" name='uclani se'>uclani se<i class="fa fa-angle-right"></i></button>
                        <p style="display: none;" name="disc2">Cena sa popustom: {{36000 - (36000 * discount)}}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
<!-- Credits to http://labs.bootstrapthemes.co/demo/Resource/PricingTableStyleDemo38/ -->
    </div>
  </template>

<script>
export default {
  props: ['user'],
  data () {
    return {
      discount: 0
    }
  },
  methods: {
    buyMembership: function (e) {
      const axios = require('axios')
      console.log(e.target)
      if (e.target.name === 'Uclani se') {
        axios.post('http://localhost:8082/rest/buyMembership/', {username: this.user, type: 'MONTHLY', dailyLogs: 3, price: 3000}).then(response => {
          if (response.data === 400) {
            console.log('Nesto nije u redu')
          } else {
            window.alert('Uspesno ste kupili clanarinu!')
            this.$router.push({name: 'Index'})
          }
        })
      } else if (e.target.name === 'uclani se') {
        axios.post('http://localhost:8082/rest/buyMembership/', {username: this.user, type: 'ANNUAL', dailyLogs: 10, price: 36000}).then(response => {
          if (response.data === 400) {
            console.log('Nesto nije u redu')
          } else {
            window.alert('Uspesno ste kupili clanarinu!')
            this.$router.push({name: 'Index'})
          }
        })
      }
    },
    calculateDiscount: function (num) {
      var p = document.getElementsByName('disc' + num)[0]
      p.style.display = 'block'
    },
    hideDiscount: function (num) {
      var p = document.getElementsByName('disc' + num)[0]
      p.style.display = 'none'
    }
  },
  mounted () {
    const axios = require('axios')
    axios.post('http://localhost:8082/rest/getDiscount/', {username: this.user}).then(response => {
      this.discount = response.data
      console.log(this.discount)
    })
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
    .demo
{
padding: 50px 0;
}
.heading-title
{
margin-bottom: 50px;
}

.pricingTable{
    border: 2px solid #e3e3e3;
    text-align: center;
    position: relative;
    padding-bottom: 40px;
    transform: translateZ(0px);
}

.pricingTable:before,
.pricingTable:after{
    content: "";
    position: absolute;
    top: -2px;
    left: -2px;
    bottom: -2px;
    right: -2px;
    z-index: -1;
    transition: all 0.5s ease 0s;
}

.pricingTable:before{
    border-right: 2px solid #08c6aa;
    border-left: 2px solid #08c6aa;
    transform: scaleY(0);
    transform-origin: 100% 0 0;
}

.pricingTable:after{
    border-bottom: 2px solid #08c6aa;
    border-top: 2px solid #08c6aa;
    transform: scaleX(0);
    transform-origin: 0 100% 0;
}

.pricingTable:hover:before{
    transform: scaleY(1);
}

.pricingTable:hover:after{
    transform: scaleX(1);
}

.pricingTable .pricingTable-header{
    background: #08c6aa;
    color: #fff;
    margin: -2px -2px 35px;
    padding: 40px 0;
}

.pricingTable .heading{
    font-size: 30px;
    font-weight: 600;
    margin: 0 0 5px 0;
}

.pricingTable .subtitle{
    font-size: 14px;
    display: block;
}

.pricingTable .price-value{
    font-size: 72px;
    font-weight: 600;
    margin-top: 10px;
    position: relative;
    display: inline-block;
}

.pricingTable .currency{
    font-size: 45px;
    font-weight: normal;
    position: absolute;
    top: 2px;
    left: -30px;
}

.pricingTable .month{
    font-size: 20px;
    position: absolute;
    bottom: 17px;
    right: -40px;
}

.pricingTable .pricing-content{
    list-style: none;
    padding: 0;
    margin-bottom: 30px;
}

.pricingTable .pricing-content li{
    font-size: 16px;
    color: #7a7e82;
    line-height: 40px;
}

.pricingTable .read{
    display: inline-block;
    border: 2px solid #7a7e82;
    border-right: none;
    font-size: 14px;
    font-weight: 700;
    color: #7a7e82;
    padding: 9px 30px;
    position: relative;
    text-transform: uppercase;
    transition: all 0.3s ease 0s;
}

.pricingTable .read:hover{
    border-color: #08c6aa;
    color: #08c6aa;
}

.pricingTable .read i{
    font-size: 19px;
    margin-top: -10px;
    position: absolute;
    top: 50%;
    right: 15px;
    transition: all 0.3s ease 0s;
}

.pricingTable .read:hover i{
    right: 5px;
}

.pricingTable .read:before,
.pricingTable .read:after{
    content: "";
    display: block;
    height: 30px;
    border-left: 2px solid #7a7e82;
    position: absolute;
    right: -11px;
    transition: all 0.3s ease 0s;
}

.pricingTable .read:before{
    bottom: -6px;
    transform: rotate(45deg);
}

.pricingTable .read:after{
    top: -6px;
    transform: rotate(-45deg);
}

.pricingTable .read:hover:before,
.pricingTable .read:hover:after{
    border-left-color: #08c6aa;
}

@media screen and (max-width: 990px){
    .pricingTable{ margin-bottom: 25px; }
    }
</style>
