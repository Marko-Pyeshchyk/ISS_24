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
with Diagram('norArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx1', graph_attr=nodeattr):
          nor=Custom('nor','./qakicons/symActorSmall.png')
          mock_user=Custom('mock_user','./qakicons/symActorSmall.png')
     mock_user >> Edge(color='blue', style='solid',  decorate='true', label='<in &nbsp; >',  fontcolor='blue') >> nor
     nor >> Edge(color='blue', style='solid',  decorate='true', label='<out &nbsp; >',  fontcolor='blue') >> mock_user
diag
