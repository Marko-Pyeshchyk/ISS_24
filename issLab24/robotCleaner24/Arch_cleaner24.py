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
with Diagram('cleaner24Arch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxcleaner24', graph_attr=nodeattr):
          cleaner24=Custom('cleaner24','./qakicons/symActorWithobjSmall.png')
          sonarmock=Custom('sonarmock','./qakicons/symActorSmall.png')
          sentinel=Custom('sentinel','./qakicons/symActorSmall.png')
     sonarmock >> Edge( label='alarm', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='alarm', **evattr, decorate='true', fontcolor='darkgreen') >> sentinel
     sentinel >> Edge(color='blue', style='solid',  decorate='true', label='<pause &nbsp; >',  fontcolor='blue') >> cleaner24
diag
