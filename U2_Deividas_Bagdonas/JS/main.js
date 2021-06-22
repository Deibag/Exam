let data = [
    {"title" : "Java programuotojas", "text" : "Jaunesnysis Java programuotojas turi gebėti atlikti nesudėtingų informacinių sistemų, jų sudėtinių dalių kūrimo bei priežiūros darbus Java programavimo kalba, apimant išeities programinio kodo rašymą bei programinės įrangos konstravimą, duomenų saugojimą ir apdorojimą reliacinėse duomenų bazėse, programinės įrangos diegimą, konfigūravimą."},
    {"title" : "JavaScript programuotojas", "text" : "Būsimasis JavaScript programuotojas mokysis projektuoti ir kurti vartotojo kompiuteriuose, išmaniuosiuose telefonuose, planšetėse ir kt. prieigos įrenginiuose naudojamą programinę įrangą (angl. front-end), parengtą jungtis prie serverių sistemų, duomenų bazių, valdyti programavimo aplinką ir kūrimo procesą."},
    {"title" : "PHP programuotojas", "text" : "PHP programuotojas gebės projektuoti ir kurti pilnai funkcionuojančias informacines sistemas (angl. full stack), tam panaudojant nesudėtingas duomenų bazes, valdyti programavimo aplinką ir kūrimo procesą."}
]

document.getElementById('h1').innerHTML = data[0].title
document.getElementById('p1').innerHTML = data[0].text

document.getElementById('h2').innerHTML = data[1].title
document.getElementById('p2').innerHTML = data[1].text

document.getElementById('h3').innerHTML = data[2].title
document.getElementById('p3').innerHTML = data[2].text


function alert(){
    window.alert("Sekmingai uzsisakete naujienlaiski");
}



