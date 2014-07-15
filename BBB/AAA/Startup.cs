using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(AAA.Startup))]
namespace AAA
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
