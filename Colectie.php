<?php
$lang = 'ro';
if(isset($_GET['lang']) && in_array($_GET['lang'], ['en','ro','ru'])) $lang = $_GET['lang'];

// Modelele tale originale
$base = [
    ['name'=>'Covor Persan Royal','type'=>'Persan','desc'=>'Un covor persan autentic lucrat manual cu motive tradiționale elaborate. Fiecare fir este țesut cu măiestrie de meșteri cu experiență.','price_new'=>'350 Lei','price_old'=>'450 Lei','dimensions'=>'300 x 200 cm','discount'=>'-22%'],
    ['name'=>'Covor Persan Imperial','type'=>'Persan','desc'=>'Cel mai elaborat covor persan cu motive complexe persane.','price_new'=>'350 Lei','price_old'=>'-','dimensions'=>'400 x 300 cm','discount'=>''],
    ['name'=>'Covor Modern Abstract','type'=>'Modern','desc'=>'Design contemporan cu motive abstracte vibrante.','price_new'=>'350 Lei','price_old'=>'-','dimensions'=>'250 x 180 cm','discount'=>''],
    ['name'=>'Covor Artistic Premium','type'=>'Modern','desc'=>'O piesă de colecție cu design artistic unic.','price_new'=>'350 Lei','price_old'=>'400 Lei','dimensions'=>'280 x 200 cm','discount'=>'-13%'],
    ['name'=>'Covor Oriental Clasic','type'=>'Tradițional','desc'=>'Eleganță orientală cu motive geometrice rafinate.','price_new'=>'350 Lei','price_old'=>'-','dimensions'=>'350 x 250 cm','discount'=>''],
    ['name'=>'Covor Traditional Moldovenesc','type'=>'Tradițional','desc'=>'Inspirat din tradițiile moldovenești cu motive florale și geometrice.','price_new'=>'350 Lei','price_old'=>'-','dimensions'=>'200 x 150 cm','discount'=>'']
];

// Exceptii pentru extensie JPG
$jpgExceptions = [33, 34, 58];

// Lista finala
$collection = [];
$index = 1;

// Construim 60 covoare
for($i = 0; $i < 60; $i++){
    $baseItem = $base[$i % 6];

    // Forțăm Image5 să fie PNG și să nu se dubleze
    if($index === 5){
        $ext = 'png';
    } else {
        $ext = in_array($index, $jpgExceptions) ? 'jpg' : 'png';
    }

    $img = "Image".$index.".".$ext;

    $item = $baseItem;
    $item['img'] = $img;

    $item['name'] = $baseItem['name']." ".$index;

    $collection[] = $item;
    $index++;
}
?>
<!DOCTYPE html>
<html lang="<?= $lang ?>">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sherghei Covoare - Colecție</title>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&family=Roboto:wght@400;500&display=swap" rel="stylesheet">
<style>
body{margin:0;font-family:'Roboto',sans-serif;color:#111;background:#fdf6f0;scroll-behavior:smooth;}
.dark-mode{background:#111;color:#fdf6f0;}
header{position:sticky;top:0;z-index:10;background:#b5651d;color:#fff;box-shadow:0 2px 8px rgba(0,0,0,0.3);padding:15px 30px;display:flex;justify-content:space-between;align-items:center;flex-wrap:wrap;}
header h1{font-family:'Montserrat',sans-serif;font-weight:700;font-size:30px;margin:0;}
.header-contact{display:flex;gap:15px;align-items:center;}
.header-contact .phone{font-weight:600;}
.header-contact .btn{background:#fff;color:#b5651d;padding:12px 25px;border-radius:8px;text-decoration:none;font-weight:500;position:relative;transition:0.3s;}
.header-contact .btn:hover{background:#8b4315;color:#fff;transform:translateY(-2px);}
.dark-toggle{cursor:pointer;padding:10px;border-radius:8px;background:#fff;color:#b5651d;font-weight:600;transition:0.3s;}
.dark-toggle:hover{background:#b5651d;color:#fff;}
nav{display:flex;justify-content:center;gap:15px;margin:20px 0;flex-wrap:wrap;}
nav a{text-decoration:none;padding:10px 18px;background:#fff;color:#b5651d;border-radius:8px;transition:.3s;font-weight:500;}
nav a.active{background:#8b4315;color:#fff;}
nav a:hover{background:#b5651d;color:#fff;transform:translateY(-2px);}

/* Colecție covoare */
.collection{display:flex;flex-wrap:wrap;justify-content:center;gap:20px;padding:50px 20px;}
.collection-item{position:relative;width:300px;border-radius:16px;overflow:hidden;cursor:pointer;transition:transform 0.3s;}
.collection-item img{
    width:100%;
    height:250px;
    object-fit:cover;
    display:block;
    transition:transform 0.3s;
}
.collection-item:hover img{transform:scale(1.05);}
.overlay{position:absolute;top:0;left:0;width:100%;height:100%;background:rgba(0,0,0,0.65);color:#fff;display:flex;flex-direction:column;justify-content:center;align-items:center;text-align:center;padding:15px;opacity:0;transition:0.3s;}
.collection-item:hover .overlay{opacity:1;}
.overlay h3, .overlay p, .overlay .price, .overlay .dimensions{margin:5px 0;}
.btn-detail{margin-top:10px;padding:10px 20px;background:#fff;color:#b5651d;text-decoration:none;border-radius:8px;display:flex;align-items:center;gap:5px;}
.btn-detail svg{width:16px;height:16px;}
.discount{position:absolute;top:10px;left:10px;background:red;padding:5px 10px;border-radius:8px;font-weight:bold;}

/* Footer */
footer{background:#b5651d;color:#fff;padding:50px 20px;font-family:'Roboto',sans-serif;}
footer .footer-container{display:flex;flex-wrap:wrap;gap:40px;justify-content:flex-start;align-items:flex-start;}
footer .footer-col{flex:1;min-width:200px;}
footer .footer-logo img{height:50px;width:auto;display:block;margin-bottom:15px;}
footer .footer-social{display:flex;gap:10px;margin-top:5px;}
footer .footer-social a{display:flex;align-items:center;justify-content:center;width:30px;height:30px;color:#fff;transition:color 0.3s;}
footer .footer-social a:hover{color:#fff;}
footer ul{list-style:none;padding:0;margin:10px 0 20px 0;line-height:1.8;}
footer ul li a{color:#fff;text-decoration:none;transition:color 0.3s;}
footer ul li a:hover{color:#fff;}
footer p, footer strong, footer blockquote, footer span, footer li{color:#fff !important;}
footer .footer-bottom{margin-top:30px;border-top:1px solid rgba(255,255,255,0.3);padding-top:15px;font-size:14px;text-align:left;}
footer .footer-bottom a{color:#fff;text-decoration:none;margin:0 8px;}
footer .footer-bottom a:hover{color:#fff;}
@media screen and (max-width:768px){footer .footer-container{flex-direction:column;gap:25px;}}

/* Butoane filtrare */
.filter-btn{margin:0 5px 10px 5px;padding:8px 16px;background:#fff;color:#b5651d;border:none;border-radius:8px;cursor:pointer;font-weight:600;transition:0.3s;}
.filter-btn:hover{background:#b5651d;color:#fff;}
.filter-btn.active{background:#8b4315;color:#fff;}
</style>
</head>
<body>
<header id="header">
  <div style="display:flex;align-items:center;justify-content:space-between;width:100%;flex-wrap:wrap;">
    <div style="display:flex;align-items:center;gap:15px;">
      <img src="Image3.png" alt="Logo" style="height:50px;">
      <h1>Sherghei Covoare</h1>
    </div>

    <nav id="mainNav" style="display:flex;gap:15px;flex-wrap:wrap;justify-content:center;flex:1;">
      <a href="Acasa.php">Acasă</a>
      <a href="Colectie.php" class="active">Colecție</a>
      <a href="DespreNoi.php">Despre Noi</a>
      <a href="Contact.php">Contact</a>
	<a href="CosulMeu.php">Cosul Meu</a>
	<a href="ContulMeu.php">Contul Meu</a>
    </nav>

    <div class="header-contact" style="display:flex;gap:15px;align-items:center;">
      <span class="phone">+40 123 456 789</span>
      <div class="dark-toggle" onclick="toggleDarkMode()">🌙</div>
    </div>

    <div class="menu-toggle" onclick="toggleMenu()">
      <div></div>
      <div></div>
      <div></div>
    </div>

  </div>
</header>

<h2 style="text-align:center;margin-top:30px;">Colecția Noastră</h2>
<p style="text-align:center;">Explorează selecția noastră de covoare premium din categorii diverse</p>

<!-- Butoane filtrare -->
<div style="text-align:center;margin:20px 0;">
    <button class="filter-btn active" data-filter="Toate">Toate</button>
    <button class="filter-btn" data-filter="Persan">Persane</button>
    <button class="filter-btn" data-filter="Modern">Moderne</button>
    <button class="filter-btn" data-filter="Tradițional">Tradiționale</button>
</div>

<div class="collection">
<?php foreach($collection as $c): ?>
  <div class="collection-item" data-type="<?= $c['type'] ?>">
    <img src="<?= $c['img'] ?>" alt="<?= $c['name'] ?>">
    <?php if($c['discount']): ?><span class="discount"><?= $c['discount'] ?></span><?php endif; ?>
    <div class="overlay">
      <p><?= $c['type'] ?></p>
      <h3><?= $c['name'] ?></h3>
      <p><?= $c['desc'] ?></p>
      <p class="price"><strong><?= $c['price_new'] ?></strong> <?php if($c['price_old']!=='-') echo "<del>".$c['price_old']."</del>"; ?></p>
      <p class="dimensions"><?= $c['dimensions'] ?></p>
      <a href="Detalii.php?item=<?= urlencode($c['name']) ?>" class="btn-detail">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <path d="M2.062 12.348a1 1 0 0 1 0-.696 10.75 10.75 0 0 1 19.876 0 1 1 0 0 1 0 .696 10.75 10.75 0 0 1-19.876 0"></path>
        </svg>
        Vezi Detalii
      </a>
    </div>
  </div>
<?php endforeach; ?>
</div>

<footer id="footer">
  <div class="footer-container">

    <div class="footer-col">
      <div class="footer-logo">
        <img src="Image3.png" alt="Sherghei Covoare">
      </div>
      <p>De peste 20 de ani aduci covoare traditionale si moderne in casa ta.</p>

      <div class="footer-social">
        <a href="https://www.facebook.com/maestro.fortunato/" target="_blank" title="Facebook">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M18 2h-3a4 4 0 0 0-4 4v3H8v4h3v8h4v-8h3l1-4h-4V6a1 1 0 0 1 1-1h3z"/>
          </svg>
        </a>

        <a href="https://www.instagram.com/hariharago?igsh=MXd5dHd5ZzY2ZnBpaw==" target="_blank" title="Instagram">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="2" y="2" width="20" height="20" rx="5" ry="5"/>
            <path d="M16 11.37a4 4 0 1 1-7.94 1.26 4 4 0 0 1 7.94-1.26z"/>
            <line x1="17.5" y1="6.5" x2="17.51" y2="6.5"/>
          </svg>
        </a>
      </div>
    </div>

    <div class="footer-col">
      <strong>Navigare</strong>
      <ul>
        <li><a href="Acasa.php">Acasa</a></li>
        <li><a href="Colectie.php">Colectie</a></li>
        <li><a href="DespreNoi.php">Despre Noi</a></li>
        <li><a href="Contact.php">Contact</a></li>
        <li><a href="CosulMeu.php">Cosul Meu</a></li>
		 <li><a href="ContulMeu.php">Contul Meu</a></li>
      </ul>
    </div>

    <div class="footer-col">
      <strong>Servicii</strong>
      <ul>
        <li><a href="#">Vanzare covoare</a></li>
        <li><a href="#">Curatare profesionala</a></li>
        <li><a href="#">Restaurare covoare</a></li>
        <li><a href="#">Consultanta gratuita</a></li>
        <li><a href="#">Livrare la domiciliu</a></li>
      </ul>
    </div>

    <div class="footer-col">
      <strong>Contact</strong>
      <p>Adresa, Str. Covoarelor Nr. 12, Bucuresti</p>
      <p>Telefon, +40 123 456 789</p>
      <p>Email, contact@sherghei-covoare.ro</p>
      <p>Program, Luni Sambata 9 18</p>
    </div>

  </div>

  <div class="footer-bottom">
    <p>© 2024 Sherghei Covoare. Toate drepturile rezervate.</p>
    <a href="#">Termeni si conditii</a> |
    <a href="#">Politica de confidentialitate</a>
  </div>
</footer>



<script>
  // Meniu mobil & Dark Mode
  function toggleMenu(){ document.getElementById('mainNav').classList.toggle('mobile-open'); }
  window.toggleMenu = toggleMenu;

  function toggleDarkMode(){
    document.body.classList.toggle('dark-mode');
    document.querySelector('header').classList.toggle('dark-mode');
    document.querySelector('footer').classList.toggle('dark-mode');
  }
  window.toggleDarkMode = toggleDarkMode;

  // Filtrare colecție
  const filterButtons = document.querySelectorAll('.filter-btn');
  const items = document.querySelectorAll('.collection-item');

  filterButtons.forEach(btn => {
      btn.addEventListener('click', () => {
          // Setare buton activ
          filterButtons.forEach(b => b.classList.remove('active'));
          btn.classList.add('active');

          const filter = btn.dataset.filter;
          items.forEach(item => {
              if(filter === 'Toate' || item.dataset.type === filter){
                  item.style.display = 'block';
              } else {
                  item.style.display = 'none';
              }
          });
      });
  });
</script>
</body>
</html>
