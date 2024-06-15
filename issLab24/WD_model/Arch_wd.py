### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('wdArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_bosco', graph_attr=nodeattr):
          sonar=Custom('sonar','./qakicons/symActorSmall.png')
          led_blu=Custom('led_blu','./qakicons/symActorSmall.png')
          fotocamera=Custom('fotocamera','./qakicons/symActorSmall.png')
     with Cluster('ctx_stazione', graph_attr=nodeattr):
          stazione=Custom('stazione','./qakicons/symActorSmall.png')
          elaboratore=Custom('elaboratore','./qakicons/symActorSmall.png')
          led_rosso=Custom('led_rosso','./qakicons/symActorSmall.png')
     sonar >> Edge( label='detection', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonar >> Edge( label='detectionEND', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='detection', **evattr, decorate='true', fontcolor='darkgreen') >> led_blu
     sys >> Edge( label='detectionEND', **evattr, decorate='true', fontcolor='darkgreen') >> led_blu
     sys >> Edge( label='detection', **evattr, decorate='true', fontcolor='darkgreen') >> fotocamera
     sys >> Edge( label='detectionEND', **evattr, decorate='true', fontcolor='darkgreen') >> led_rosso
     stazione >> Edge(color='blue', style='solid',  decorate='true', label='<turnOn &nbsp; >',  fontcolor='blue') >> sonar
     fotocamera >> Edge(color='blue', style='solid',  decorate='true', label='<foto &nbsp; >',  fontcolor='blue') >> elaboratore
     elaboratore >> Edge(color='blue', style='solid',  decorate='true', label='<turnOn &nbsp; >',  fontcolor='blue') >> led_rosso
diag
