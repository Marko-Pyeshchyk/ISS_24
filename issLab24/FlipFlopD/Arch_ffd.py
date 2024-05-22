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
with Diagram('ffdArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_ffd', graph_attr=nodeattr):
          nor_s=Custom('nor_s','./qakicons/symActorSmall.png')
          nor_r=Custom('nor_r','./qakicons/symActorSmall.png')
          mock_user=Custom('mock_user','./qakicons/symActorSmall.png')
     nor_s >> Edge(color='blue', style='solid',  decorate='true', label='<out &nbsp; >',  fontcolor='blue') >> nor_r
     mock_user >> Edge(color='blue', style='solid',  decorate='true', label='<in &nbsp; >',  fontcolor='blue') >> nor_s
     nor_r >> Edge(color='blue', style='solid',  decorate='true', label='<out &nbsp; >',  fontcolor='blue') >> nor_s
     mock_user >> Edge(color='blue', style='solid',  decorate='true', label='<in &nbsp; >',  fontcolor='blue') >> nor_r
diag
