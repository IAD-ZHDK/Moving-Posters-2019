package Posters;

import processing.core.*;


public class Poster4 extends Poster{

    private String BigX = "1310 1443 1555 1711 1849 1260 1366 1335 1419 1535 1617 1734 1926 1255 1365 1476 1602 1638 1734 1884 1853 1353 1262 1452 1554 1635 1824 1923 1449 1350 1264 1312 1498 1592 1648 1737 1824 1766 1911 1367 1499 1646 1768 1910 1943 1796 1628 1448 1262 1425 1337 1245 1597 1722 1842 1942 1855 1740 1619 1493 1379 1267 1412 1533 1768 1925";
    private String BigY = "82 83 97 83 96 334 313 436 381 371 380 371 371 542 644 642 600 536 539 539 603 745 838 823 757 799 819 782 951 1013 1038 1111 1108 941 1105 976 1018 1105 1023 1227 1245 1230 1246 1255 1339 1390 1393 1375 1390 1507 1563 1581 1510 1504 1561 1586 1656 1675 1665 1678 1664 1828 1831 1848 1833 1770";

    private String MediumX = "1255 1391 1498 1612 1650 1791 1908 1941 1316 1251 1252 1302 1358 1479 1677 1791 1872 1350 1310 1245 1311 1428 1506 1532 1555 1683 1793 1815 1941 1958 1899 1253 1299 1359 1321 1398 1533 1584 1662 1618 1695 1737 1768 1828 1863 1871 1896 1936 1300 1256 1362 1368 1413 1509 1542 1647 1687 1800 1875 1865 1944 1892 1825 1713 1592 1561 1463 1425 1256 1310 1423 1556 1593 1713 1863 1894 1842 1891 1717 1568 1500 1386 1340 1329 1273 1440 1556 1682 1801 1920 1913 1796 1663 1545 1488 1364 1288 1313 1349 1479 1605 1679 1713 1833 1877 1845 1941";
    private String MediumY = "70 108 105 109 73 105 71 104 303 391 445 379 376 358 358 390 357 529 560 603 640 620 594 639 593 568 507 559 561 607 639 733 759 802 850 850 812 848 850 743 810 850 808 732 769 845 730 845 983 1114 1069 955 994 932 972 976 933 947 974 1060 1085 1116 1116 1077 1112 1084 1079 1117 1246 1212 1246 1214 1250 1214 1225 1312 1322 1376 1394 1378 1396 1399 1361 1627 1638 1648 1690 1654 1675 1642 1535 1521 1522 1487 1523 1504 1528 1855 1816 1822 1859 1820 1852 1852 1797 1756 1855";

    private String VerySmallX = "1241 1269 1353 1362 1361 1401 1488 1516 1588 1617 1663 1753 1761 1756 1785 1817 1899 1952 1245 1272 1320 1398 1386 1387 1294 1285 1470 1502 1575 1584 1666 1695 1780 1812 1837 1835 1830 1864 1891 1387 1321 1296 1220 1285 1309 1343 1273 1350 1376 1423 1470 1575 1536 1559 1590 1592 1656 1685 1688 1722 1761 1780 1832 1845 1863 1908 1923 1940 1862 1829 1803 1290 1317 1242 1268 1242 1295 1314 1323 1359 1370 1395 1419 1487 1499 1497 1517 1550 1567 1593 1595 1623 1662 1665 1702 1728 1775 1806 1818 1882 1905 1903 1863 1928 1953 1953 1244 1319 1330 1360 1375 1390 1403 1426 1404 1401 1460 1498 1552 1551 1604 1647 1686 1698 1725 1730 1767 1778 1792 1792 1815 1830 1871 1834 1849 1859 1860 1902 1927 1951 1951 1242 1272 1305 1335 1416 1442 1461 1457 1470 1555 1611 1689 1724 1746 1802 1833 1830 1812 1833 1860 1953 1869 1851 1854 1809 1770 1749 1753 1728 1689 1675 1665 1595 1546 1527 1541 1503 1407 1381 1343 1316 1306 1251 1321 1392 1368 1297 1299 1302 1330 1433 1537 1572 1662 1698 1788 1821 1867 1898 1878 1830 1783 1759 1683 1645 1536 1507 1479 1244 1310 1364 1455 1486 1571 1581 1605 1637 1641 1644 1670 1721 1829 1839 1805 1804 1805 1881 1868 1886 1905 1909 1949";
    private String VerySmallY= "115 105 115 86 61 58 58 70 58 58 115 115 85 58 61 58 114 57 291 292 340 346 413 454 456 416 400 402 402 348 400 402 349 352 348 379 400 402 400 517 517 516 576 588 601 597 632 566 597 659 600 634 566 547 558 529 593 525 499 496 492 556 498 528 504 586 610 648 647 651 649 722 719 766 777 793 796 815 790 861 834 802 801 855 831 800 855 855 805 812 784 856 755 733 858 799 858 861 768 804 825 857 718 718 720 742 1078 1059 955 1126 1105 1125 1081 1072 959 936 1126 985 919 1125 1072 920 983 1125 1125 925 929 1003 982 1068 1081 1063 1022 969 950 1096 1125 1076 1124 1124 1050 1216 1201 1261 1260 1203 1208 1231 1260 1202 1251 1206 1261 1261 1204 1209 1209 1252 1276 1294 1276 1285 1345 1364 1392 1342 1349 1375 1407 1352 1366 1408 1350 1351 1350 1369 1410 1354 1367 1351 1408 1393 1351 1352 1507 1550 1606 1575 1605 1665 1671 1692 1645 1651 1702 1701 1629 1612 1603 1582 1507 1497 1483 1544 1477 1476 1530 1477 1479 1867 1811 1862 1864 1865 1809 1831 1809 1809 1840 1866 1864 1810 1817 1794 1798 1771 1747 1749 1838 1861 1828 1858 1816";

    private String VerySmallX2 = "495 485 500 530 547 562 589 604 601 550 586 604 586 557 526 498 490 496 604 577 550 521 490 490 490 603 577 549 520 490 604 602 603 573 546 518 490 604 578 551 524 497 487 498 522 549 577 605 490 519 547 576 603 602 584 546 509 490 490 491 491 518 546 546 574 604 604 604 522";
    private String VerySmallY2 = "61 89 111 113 90 66 61 83 111 306 311 341 375 386 384 375 347 321 582 582 582 583 582 609 637 823 822 823 822 823 1029 1059 1090 1060 1059 1059 1059 1281 1280 1281 1280 1284 1311 1338 1343 1346 1345 1343 1550 1551 1551 1551 1551 1584 1611 1618 1611 1583 1821 1848 1875 1821 1821 1850 1821 1822 1850 1877 309";

    private int[] BigXList;
    private int[] BigYList;

    private int[] MediumXList;
    private int[] MediumYList;

    private int[] SmallXList;
    private int[] SmallYList;

    private int[] VerySmallXList;
    private int[] VerySmallYList;

    private int[] VerySmallXList2;
    private int[] VerySmallYList2;

    private PImage backgroundPic;
    private PImage bubblePic;
    private PImage bubblePic2;
    private float scaleFactor = 1;


    public Poster4(PApplet parent, boolean DEBUG) {
        super(parent, DEBUG);
        p.rectMode(p.CENTER);

        p.rectMode(p.CENTER);
        p.fill(255);

        // loading images

        backgroundPic = p.loadImage("Poster4/BG6.jpg");
        bubblePic = p.loadImage("Poster4/Dot_In_Final.png");
        bubblePic2 = p.loadImage("Poster4/Dot_Out_Final.png");

        // splitting Strings of coordinates

        BigXList =  parseStringArray(BigX);
        BigYList =  parseStringArray(BigY);

        MediumXList =  parseStringArray(MediumX);
        MediumYList =  parseStringArray(MediumY);

        VerySmallXList =  parseStringArray(VerySmallX);
        VerySmallYList =  parseStringArray(VerySmallY);

        VerySmallXList2 = parseStringArray(VerySmallX2);
        VerySmallYList2 = parseStringArray(VerySmallY2);

        if (DEBUG) {
            scaleFactor = (float)(p.width) / 2160;
            backgroundPic.resize(p.width, p.height);
            bubblePic.resize(p.floor(bubblePic2.width * scaleFactor), p.floor(bubblePic2.height * scaleFactor));
            bubblePic2.resize(p.floor((bubblePic2.width * scaleFactor)), p.floor(bubblePic2.height * scaleFactor));
            responsify(BigXList);
            responsify(BigYList);
            responsify(MediumXList);
            responsify(MediumYList);
            responsify(VerySmallXList);
            responsify(VerySmallYList);
            responsify(VerySmallXList2);
            responsify(VerySmallYList2);
        }
    }

    public boolean draw(PVector Pos) {
        PVector TargetPos = Pos.copy();
        TargetPos.x = 1-TargetPos.x;
        TargetPos.x = TargetPos.x*p.width;
        TargetPos.y = TargetPos.y*p.height;
        p.constrain(TargetPos.x, 0, p.width);
        p.imageMode(p.CORNER);
        p.image(backgroundPic, 0, 0);
        p.imageMode(p.CENTER);


        // mapping Camera-Input  TargetPos.x for size variation

        float dBig = p.map(TargetPos.x, 300*scaleFactor, p.width-(1000*scaleFactor), -45*scaleFactor, 90*scaleFactor);  //TargetPos.x
        float dMedium = p.map(TargetPos.x, 300*scaleFactor, p.width-(1000*scaleFactor), -50*scaleFactor, 65*scaleFactor);  //TargetPos.x
        float dVerySmall = p.map(TargetPos.x, 300*scaleFactor, p.width-(1000*scaleFactor), -35*scaleFactor, 35*scaleFactor);  //TargetPos.x
        float dVerySmall2 = p.map(TargetPos.x, 50*scaleFactor, p.width-(1100*scaleFactor), 0, 70*scaleFactor);  //TargetPos.x

        if (dVerySmall2 >= 35*scaleFactor) {
            dVerySmall2 =35*scaleFactor;
        }

        // LOUDEST I

        for (int i = 0; i < 233; i++) {
            p.pushMatrix();
            p.image(bubblePic, VerySmallXList[i], VerySmallYList[i], 25*scaleFactor, 25*scaleFactor);
            p.popMatrix();
            if (dVerySmall >= 0) {
                p.pushMatrix();
                p.image(bubblePic2, VerySmallXList[i], VerySmallYList[i], dVerySmall, dVerySmall);
                p.popMatrix();
            }
        }

        for (int i = 0; i < 107; i++) {
            p.pushMatrix();
            p.image(bubblePic, MediumXList[i], MediumYList[i], 60*scaleFactor, 60*scaleFactor);
            p.popMatrix();
            if (dMedium >= 0) {
                p.pushMatrix();
                p.image(bubblePic2, MediumXList[i], MediumYList[i], dMedium, dMedium);
                p.popMatrix();
            }
        }


        for (int i = 0; i < 66; i++) {

            p.pushMatrix();
            p.image(bubblePic, BigXList[i], BigYList[i], 70*scaleFactor, 70*scaleFactor);
            p.popMatrix();
            if (dBig >= 0) {
                p.pushMatrix();
                p.image(bubblePic2, BigXList[i], BigYList[i], dBig, dBig);
                p.popMatrix();
            }
        }

        // SOLITUDE

        for (int i = 0; i < 69; i++) {
            p.image(bubblePic, VerySmallXList2[i], VerySmallYList2[i], 27*scaleFactor, 27*scaleFactor);
            if (dVerySmall2 >= 0) {
                p.pushMatrix();
                p.image(bubblePic2, VerySmallXList2[i], VerySmallYList2[i], dVerySmall2, dVerySmall2);
                p.popMatrix();
            }
        }
        return true;
    }

    private int[] parseStringArray (String list) {
        String[] stringArray = p.split(list, ' ');
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] =Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }

    private void responsify (int[] list) {
        for (int i = 0; i < list.length; i++) {
            int X =  Math.round((float)(list[i])*scaleFactor);
            list[i] = X;
        }
    }

}